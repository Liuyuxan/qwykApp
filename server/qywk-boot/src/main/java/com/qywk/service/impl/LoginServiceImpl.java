package com.qywk.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qywk.common.constant.CodeStateEnum;
import com.qywk.common.constant.EnableConstants;
import com.qywk.common.entity.ResultBody;
import com.qywk.mapper.UserMapper;
import com.qywk.pojo.ao.ForgetAO;
import com.qywk.pojo.ao.LoginAO;
import com.qywk.pojo.ao.RegisterAO;

import com.qywk.pojo.dto.UserDTO;
import com.qywk.service.LoginService;
import com.qywk.utils.JwtUtils;
import com.qywk.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author QiLinHu
 * @date 2024/03/01 0:01
 * @description
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserMapper userMapper;
    /**
     * 普通登录
     * @param ao
     * @return
     */
    @Override
    public ResultBody normal(LoginAO ao) {
        QueryWrapper<UserDTO> wrapper = new QueryWrapper<>();

        // 账号类型判断
        if(ao.getUsername().length() == 11) wrapper.eq("phone_num", ao.getUsername());
        else wrapper.eq("username", ao.getUsername());

        UserDTO user = userMapper.selectOne(wrapper);
        if(user == null || user.getEnable().equals(EnableConstants.DELETE)){
            return ResultBody.error().message("手机号或用户名不存在").code(CodeStateEnum.LOGIN_USER_NOT_NULL.code);
        }

        if(MD5Utils.getMD5String(ao.getPassword()).equals(user.getPassword())){
            return ResultBody.ok().message("登录成功").data("token", JwtUtils.getToken(user));
        }

        return ResultBody.error().message("密码错误").code(CodeStateEnum.LOGIN_PASSWORD_FAIL.code);
    }

    /**
     * 微信绑定快速登录
     * @param code
     * @return
     */
    @Override
    public ResultBody fast(String code) {
        return ResultBody.error().message("暂未开发");
    }

    /**
     * 注册用户
     * @param ao
     * @return
     */
    @Override
    public ResultBody register(RegisterAO ao) {
        QueryWrapper<UserDTO> wrapper = new QueryWrapper<>();
        wrapper.eq("phone_num", ao.getPhoneNum());

        // 判断是否已注册
        if(userMapper.selectOne(wrapper) != null){
            return ResultBody.error().code(CodeStateEnum.LOGIN_USER_TO_REGISTER.code)
                    .message(CodeStateEnum.LOGIN_USER_TO_REGISTER.message);
        }

        // todo 验证码校验

        // 强密码校验
        if(!strongCryptographicCheck(ao.getPassword())){
            return ResultBody.error().code(CodeStateEnum.SERVER_ERROR_PARAM.code)
                    .message("密码包含至少一个大写字母或一个小写字母和一个数字");
        }

        // 尝试4次创建防止用户冲突
        for(int i = 0; i < 4; i++){
            UserDTO user = new UserDTO();
            // 生成用户id
            user.setUsername(generateUserAccount(i));
            BeanUtil.copyProperties(ao, user);
            user.setPassword(MD5Utils.getMD5String(user.getPassword()));
            if(userMapper.insert(user) >= 1){
                return ResultBody.ok().message("创建成功").data("username", user.getUsername());
            }
        }

        return ResultBody.error().code(CodeStateEnum.ERROR.code).message(CodeStateEnum.ERROR.message + ",请稍后重试");
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

    public static boolean strongCryptographicCheck(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        // 正则表达式匹配：密码包含至少一个大写字母或一个小写字母和一个数字
        String regex = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$";
        return Pattern.compile(regex).matcher(password).matches();
    }

    /**
     * 忘记密码
     * @param ao
     * @return
     */
    @Override
    public ResultBody forget(ForgetAO ao) {
        return ResultBody.error().message("暂未开发");
    }

    /**
     * 修改密码
     * @param ao
     * @param newPassword
     * @return
     */
    @Override
    public ResultBody update(LoginAO ao, String newPassword) {
        // 强密码校验
        if(!strongCryptographicCheck(newPassword)){
            return ResultBody.error().code(CodeStateEnum.SERVER_ERROR_PARAM.code)
                    .message("密码包含至少一个大写字母或一个小写字母和一个数字");
        }

        QueryWrapper<UserDTO> wrapper = new QueryWrapper<>();
        if(ao.getUsername().length() == 11) wrapper.eq("phone_num", ao.getUsername());
        else wrapper.eq("username", ao.getUsername());
        UserDTO user = userMapper.selectOne(wrapper);

        // 判断用户是否为注册使用
        if(user == null || user.getEnable().equals(EnableConstants.DELETE)){
            return ResultBody.error().code(CodeStateEnum.LOGIN_USER_NOT_NULL.code).
                    message(CodeStateEnum.LOGIN_USER_NOT_NULL.message);
        }

        // 旧密码校验
        if(!user.getPassword().equals(MD5Utils.getMD5String(ao.getPassword()))){
            return ResultBody.error().code(CodeStateEnum.LOGIN_PASSWORD_FAIL.code).
                    message(CodeStateEnum.LOGIN_PASSWORD_FAIL.message);
        }

        // 修改密码
        user.setPassword(MD5Utils.getMD5String(newPassword));

        return userMapper.updateById(user) > 0 ?
                ResultBody.ok().message("修改成功") :
                ResultBody.error().code(CodeStateEnum.LOGIN_USER_NOT_NULL.code).
                        message(CodeStateEnum.LOGIN_USER_NOT_NULL.message);
    }

    @Override
    public ResultBody sendVerify(String num) {
        return null;
    }
}
