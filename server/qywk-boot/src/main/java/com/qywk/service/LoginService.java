package com.qywk.service;

import com.qywk.common.entity.ResultBody;
import com.qywk.pojo.ao.ForgetAO;
import com.qywk.pojo.ao.LoginAO;
import com.qywk.pojo.ao.RegisterAO;

/**
 * @author QiLinHu
 * @date 2024/03/01 0:00
 * @description
 */
public interface LoginService {
    ResultBody normal(LoginAO ao);

    ResultBody fast(String code);

    ResultBody register(RegisterAO ao);

    ResultBody forget(ForgetAO ao);

    ResultBody update(LoginAO ao, String newPassword);

    ResultBody sendVerify(String num);
}
