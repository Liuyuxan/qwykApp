package com.qywk.pojo.ao;

import lombok.Data;

/**
 * @author QiLinHu
 * @date 2024/03/01 21:20
 * @description 注册信息
 */
@Data
public class RegisterAO {
    /**
     * 手机号
     */
    private String phoneNum;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String code;
}
