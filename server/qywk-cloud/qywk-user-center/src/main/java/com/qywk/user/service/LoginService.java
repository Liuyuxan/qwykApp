package com.qywk.user.service;

import com.qywk.common.core.entity.ResultBody;
import com.qywk.user.pojo.ao.ChangeAO;
import com.qywk.user.pojo.ao.ForgetAO;
import com.qywk.user.pojo.ao.LoginAO;
import com.qywk.user.pojo.ao.RegisterAO;

/**
 * @author qlh
 * @date 2024/04/18 14:33
 * @description
 */
public interface LoginService {
    /**
     * 普通登录
     * @param ao
     * @return
     */
    ResultBody login(LoginAO ao);

    /**
     * 微信快速登录
     * @param code
     * @return
     */
    ResultBody fast(String code);

    /**
     * 注册
     * @param ao
     * @return
     */
    ResultBody register(RegisterAO ao);

    /**
     * 忘记密码
     * @param ao
     * @return
     */
    ResultBody forget(ForgetAO ao);

    /**
     * 修改密码
     * @param ao
     * @return
     */
    ResultBody changePassword(ChangeAO ao);

    /**
     * Send the verification code / 发送验证码
     * @param tel   手机号
     * @return
     */
    ResultBody sentCode(String tel);
}
