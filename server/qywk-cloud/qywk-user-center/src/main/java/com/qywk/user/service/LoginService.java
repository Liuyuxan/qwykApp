package com.qywk.user.service;

import com.qywk.common.core.entity.ResultBody;
import com.qywk.user.pojo.ao.LoginAO;

/**
 * @author qlh
 * @date 2024/04/18 14:33
 * @description
 */
public interface LoginService {
    ResultBody login(LoginAO ao);
}
