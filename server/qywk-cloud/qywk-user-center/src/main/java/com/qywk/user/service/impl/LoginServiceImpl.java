package com.qywk.user.service.impl;

import com.alibaba.nacos.client.config.utils.MD5;
import com.qywk.common.core.constant.SecurityConstants;
import com.qywk.common.core.entity.ResultBody;
import com.qywk.common.core.utils.JwtUtils;
import com.qywk.common.redis.customenum.RedisKeyEnum;
import com.qywk.common.redis.service.RedisService;
import com.qywk.user.mapper.UserInfoMapper;
import com.qywk.user.pojo.ao.LoginAO;
import com.qywk.user.pojo.dto.UserInfoDTO;
import com.qywk.user.service.AuthorityService;
import com.qywk.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

        // 数据库中有缓存缺失，那么重新生成token以及刷新权限
//        if (!authorityService.refresh(userInfoDTO.getUserId())) {
//            return ResultBody.error().message("后端服务器错误!用户权限刷新失败!");
//        }

        // 生成一个token并设置缓存
        String token = createUserToken(ao.getUserId(), userInfoDTO.getNickname());
        return ResultBody.ok().message("登陆成功!").data("token", token);
    }

    /**
     * 弱密码判断方法
     * @param password 密码
     * @return 是否为弱鸡密码
     * */
    private boolean isWeakPassword(String password) {

        // 长度小于八位
        if (password.length() < 8) return true;

        // 是否为纯数字
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) < '0' || password.charAt(i) > '9') return false;
        }

        return true;
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
