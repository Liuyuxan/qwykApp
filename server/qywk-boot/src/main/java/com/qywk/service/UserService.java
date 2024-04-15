package com.qywk.service;

import com.qywk.common.entity.ResultBody;

/**
 * @author qlh
 * @date 2024/03/08 14:25
 * @description
 */
public interface UserService {
    ResultBody getBaseInfo(String username);

    ResultBody getDetailInfo(String username);
}
