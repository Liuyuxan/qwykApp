package com.qywk.user.pojo.ao;

import lombok.Data;

/**
 * @author qlh
 * @date 2024/04/18 21:03
 * @description
 */
@Data
public class ForgetAO {
    // user_id
    private String userId;
    // 手机号
    private String tel;
    // 密码
    private String password;
    // 验证码
    private String code;
}
