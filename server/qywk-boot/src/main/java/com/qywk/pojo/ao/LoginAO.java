package com.qywk.pojo.ao;

import lombok.Data;

/**
 * @author QiLinHu
 * @date 2024/02/29 23:02
 * @description
 */
@Data
public class LoginAO {
    /**
     * 账号
     */
    private String userId;

    /**
     * 密码
     */
    private String password;
}
