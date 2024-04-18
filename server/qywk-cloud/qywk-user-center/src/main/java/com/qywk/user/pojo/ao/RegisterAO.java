package com.qywk.user.pojo.ao;

import lombok.Data;

/**
 * @author qlh
 * @date 2024/04/18 20:56
 * @description 注册相关
 */
@Data
public class RegisterAO {
    // 手机号
    private String tel;
    // 密码
    private String password;
    // 验证码
    private String code;
}
