package com.qywk.user.pojo.ao;

import lombok.Data;

/**
 * @author qlh
 * @date 2024/04/18 21:33
 * @description
 */
@Data
public class ChangeAO {
    // user_id
    private String userId;
    // 密码
    private String password;
    // 手机号
    private String tel;
    // 验证码
    private String code;
    // 新密码
    private String newPassword;
}
