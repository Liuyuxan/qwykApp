package com.qywk.user.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.nacos.client.config.utils.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qywk.common.core.constant.EnableConstants;
import com.qywk.common.core.constant.SecurityConstants;
import com.qywk.common.core.customenum.CodeStateEnum;
import com.qywk.common.core.entity.ResultBody;
import com.qywk.common.core.utils.JwtUtils;
import com.qywk.common.redis.customenum.RedisKeyEnum;
import com.qywk.common.redis.service.RedisService;
import com.qywk.user.clients.WeChatLoginClient;
import com.qywk.user.config.properties.WeChatProperties;
import com.qywk.user.mapper.UserInfoMapper;
import com.qywk.user.pojo.ao.ChangeAO;
import com.qywk.user.pojo.ao.ForgetAO;
import com.qywk.user.pojo.ao.LoginAO;
import com.qywk.user.pojo.ao.RegisterAO;
import com.qywk.user.pojo.dto.UserInfoDTO;
import com.qywk.user.service.AuthorityService;
import com.qywk.user.service.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * @author qlh
 * @date 2024/04/18 14:34
 * @description
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    RedisService redisService;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    WeChatLoginClient weChatLoginClient;

    @Autowired
    WeChatProperties weChatProperties;


    /**
     * 普通登录接口
     * @param ao
     * @return
     */
    @Override
    public ResultBody login(LoginAO ao) {
        // 接口限流，对单username进行限流，防止恶意爆破密码
        String limitTokenKey = RedisKeyEnum.LIMIT_USER_LOGIN.create(ao.getUserId());
        if (!redisService.hasKey(limitTokenKey)) redisService.setCacheObject(limitTokenKey, 0);
        int limit = redisService.getCacheObject(limitTokenKey);
        if (limit >= 5) {
            long expire = redisService.getExpire(limitTokenKey); // 获取过期时间
            return ResultBody.error().message("短时间尝试次数太多啦!请等待" + (expire / 60) + "分" + (expire % 60) + "秒后重试!");
        }

        // token令牌和权限树的redis键名称
        String tokenKey = RedisKeyEnum.LOGIN_TOKENS.create(ao.getUserId());
        String authKey = RedisKeyEnum.AUTH.create(ao.getUserId());

        // 获取用户对象
        UserInfoDTO userInfoDTO = userInfoMapper.selectById(ao.getUserId());
        if (userInfoDTO == null) {
            return ResultBody.error().message("未找到该用户");
        }

        // 密码正确性校验
        if (!MD5.getInstance().getMD5String(ao.getPassword()).equals(userInfoDTO.getPassword())) {
            // 密码错误一次，开始统计接口访问次数
            limit ++;
            redisService.setCacheObject(limitTokenKey, limit, 5L, TimeUnit.MINUTES);
            // 密码错误
            return ResultBody.error().message("错误的用户名或密码!你还可以尝试:" + (5 - limit) + "次!");
        }

        if (redisService.hasKey(tokenKey) && redisService.hasKey(authKey)) {
            // 登陆成功，且redis中具有令牌和权限的缓存，直接返回
            redisService.expire(tokenKey, 3L, TimeUnit.DAYS); // 重新设置缓存存在的时间
            return ResultBody.ok().message("登陆成功!").data("token", redisService.getCacheObject(tokenKey));
        }

        // todo 数据库中有缓存缺失，那么重新生成token以及刷新权限
//        if (!authorityService.refresh(userInfoDTO.getUserId())) {
//            return ResultBody.error().message("后端服务器错误!用户权限刷新失败!");
//        }

        // 生成一个token并设置缓存
        String token = createUserToken(ao.getUserId(), userInfoDTO.getNickname());
        return ResultBody.ok().message("登陆成功!").data("token", token);
    }

    /**
     * 微信绑定快速登录
     * @param code
     * @return
     */
    @Override
    public ResultBody fast(String code) {
        // 通过微信授权码请求open_id
        String str = weChatLoginClient.login(weChatProperties.getAppid(), weChatProperties.getSecret(), code, "authorization_code");
        HashMap result = JSONObject.parseObject(str, HashMap.class);

        String session_key = (String) result.get("session_key");
        String openid = (String) result.get("openid");
        Integer error_code = (Integer) result.get("errcode");

        if (session_key == null || openid == null) return ResultBody.error().message("快速登陆失败!请尝试普通登陆!错误代码:" + error_code);
        if (session_key.isEmpty() || openid.isEmpty()) return ResultBody.error().message("快速登陆失败!请尝试普通登陆!错误代码:" + error_code);

        // 拿到绑定的用户
        UserInfoDTO userInfoDTO = userInfoMapper.selectOne(new QueryWrapper<UserInfoDTO>()
                .eq("open_id", openid)
        );

        if (userInfoDTO == null) return ResultBody.error().message("此微信未绑定到一个用户，请尝试普通的登陆方式!");

//        // todo 重新生成token以及刷新权限
//        if (!authorityService.refresh(userInfoDTO.getUser_id())) {
//            return ResultBody.error().message("后端服务器错误!用户权限刷新失败!");
//        }

        // 生成一个token并设置缓存
        String token = createUserToken(userInfoDTO.getUserId(), userInfoDTO.getNickname());
        return ResultBody.ok().message("登陆成功!").data("token", token);
    }

    /**
     * 注册用户
     * @param ao
     * @return
     */
    @Override
    public ResultBody register(RegisterAO ao) {
        QueryWrapper<UserInfoDTO> wrapper = new QueryWrapper<>();
        wrapper.eq("tel", ao.getTel());

        // 判断是否已注册
        if(userInfoMapper.selectOne(wrapper) != null){
            return ResultBody.error().code(CodeStateEnum.LOGIN_USER_TO_REGISTER.code)
                    .message(CodeStateEnum.LOGIN_USER_TO_REGISTER.message);
        }

        // todo 验证码校验
        String key = RedisKeyEnum.REGISTER_TEL_CODE.create(ao.getTel());
        // todo 后续对验证码进行验证加锁，防止爆破
        if(redisService.hasKey(key)){
            String code =  redisService.getCacheObject(key);
            if(!code.equals(ao.getCode())){
                return ResultBody.error().message("验证码错误");
            }
        }

        // 强密码校验
        if(!strongCryptographicCheck(ao.getPassword())){
            return ResultBody.error().code(CodeStateEnum.SERVER_ERROR_PARAM.code)
                    .message("密码包含至少一个大写字母或一个小写字母和一个数字");
        }

        // 尝试4次创建防止用户冲突
        for(int i = 0; i < 4; i++){
            UserInfoDTO user = new UserInfoDTO();
            // 生成用户id
            user.setUserId(generateUserAccount(i));
            BeanUtils.copyProperties(ao, user);
            user.setPassword(MD5.getInstance().getMD5String(ao.getPassword()));
            if(userInfoMapper.insert(user) >= 1){
                return ResultBody.ok().message("创建成功").data("user_id", user.getUserId());
            }
        }

        return ResultBody.error().code(CodeStateEnum.ERROR.code).message(CodeStateEnum.ERROR.message + ",请稍后重试");
    }

    /**
     * 忘记密码
     * @param ao
     * @return
     */
    @Override
    public ResultBody forget(ForgetAO ao) {
        QueryWrapper<UserInfoDTO> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", ao.getUserId());

        // 判断用户是否存在
        UserInfoDTO user = userInfoMapper.selectOne(wrapper);
        if(user == null || user.getEnable().equals(EnableConstants.DELETE)){
            return ResultBody.error().code(CodeStateEnum.LOGIN_USER_NOT_NULL.code)
                    .message(CodeStateEnum.LOGIN_USER_NOT_NULL.message);
        }

        if(!user.getTel().equals(ao.getTel())){
            return ResultBody.error().message("手机号错误");
        }

        // todo 验证码校验
        String key = RedisKeyEnum.REGISTER_TEL_CODE.create(ao.getTel());
        // todo 后续对验证码进行验证加锁，防止爆破
        if(redisService.hasKey(key)){
            String code =  redisService.getCacheObject(key);
            if(!code.equals(ao.getCode())){
                return ResultBody.error().message("验证码错误");
            }
        }

        // 强密码校验
        if(!strongCryptographicCheck(ao.getPassword())){
            return ResultBody.error().code(CodeStateEnum.SERVER_ERROR_PARAM.code)
                    .message("密码包含至少一个大写字母或一个小写字母和一个数字");
        }

        user.setPassword(MD5.getInstance().getMD5String(ao.getPassword()));

        return userInfoMapper.updateById(user) > 0 ?
                ResultBody.ok().message("修改密码成功") :
                ResultBody.error().message("修改密码异常，请稍后再试！");
    }

    /**
     * 修改密码
     * @param ao
     * @return
     */
    @Override
    public ResultBody changePassword(ChangeAO ao) {
        // 强密码校验
        if(!strongCryptographicCheck(ao.getNewPassword())){
            return ResultBody.error().code(CodeStateEnum.SERVER_ERROR_PARAM.code)
                    .message("密码包含至少一个大写字母或一个小写字母和一个数字");
        }

        QueryWrapper<UserInfoDTO> wrapper = new QueryWrapper<>();
        wrapper.eq("tel", ao.getTel()).eq("user_id", ao.getUserId());
        UserInfoDTO user = userInfoMapper.selectOne(wrapper);

        // 判断用户是否为注册使用
        if(user == null || user.getEnable().equals(EnableConstants.DELETE)){
            return ResultBody.error().code(CodeStateEnum.LOGIN_USER_NOT_NULL.code).
                    message(CodeStateEnum.LOGIN_USER_NOT_NULL.message);
        }

        // 旧密码校验
        if(!user.getPassword().equals(MD5.getInstance().getMD5String(ao.getPassword()))){
            return ResultBody.error().code(CodeStateEnum.LOGIN_PASSWORD_FAIL.code).
                    message(CodeStateEnum.LOGIN_PASSWORD_FAIL.message);
        }

        // 修改密码
        user.setPassword(MD5.getInstance().getMD5String(ao.getNewPassword()));

        return userInfoMapper.updateById(user) > 0 ?
                ResultBody.ok().message("修改成功") :
                ResultBody.error().code(CodeStateEnum.LOGIN_USER_NOT_NULL.code).
                        message(CodeStateEnum.LOGIN_USER_NOT_NULL.message);
    }


    /**
     * 生成用户id
     * @return  username
     */
    public String generateUserAccount(int len) {
        int length = 6 + len; // 设置账号长度
        String characters = "0123456789"; // 不包含0，可以根据需要扩展字符集合
        Random random = new Random();
        StringBuilder userAccount = new StringBuilder(length);

        // 确保第一个字符不是0
        userAccount.append(characters.charAt(random.nextInt(characters.length() - 1) + 1));

        for (int i = 1; i < length; i++) {
            userAccount.append(characters.charAt(random.nextInt(characters.length())));
        }

        return userAccount.toString();
    }

    /**
     * 强密码校验
     * @param password  密码
     * @return true/false
     */

    public boolean strongCryptographicCheck(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        // 正则表达式匹配：密码包含至少一个大写字母或一个小写字母和一个数字
        String regex = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$";
        return Pattern.compile(regex).matcher(password).matches();
    }

    /**
     * 创建一个用户的访问token，并将这个token存入redis缓存（3天过期）
     * @param userId 用户的id
     * @param nickname 用户的姓名
     * */
    private String createUserToken(String userId, String nickname) {
        // 生成一个token
        Map<String, Object> map = new HashMap<>();
        map.put(SecurityConstants.DETAILS_USER_ID, userId);
        map.put(SecurityConstants.DETAILS_NICKNAME, nickname);
        map.put(SecurityConstants.USER_KEY, RedisKeyEnum.LOGIN_TOKENS.create(userId));
        map.put("create_time", System.currentTimeMillis()); // 加入生成token的时间戳，可以使得每次登陆的token生成不一样，间接实现单设备登陆(可以绕开)
        String token = JwtUtils.createToken(map);
        redisService.setCacheObject(RedisKeyEnum.LOGIN_TOKENS.create(userId), token, 3L, TimeUnit.DAYS); // 加入redis缓存中，过期时间设置为3天
        return token;
    }
}
