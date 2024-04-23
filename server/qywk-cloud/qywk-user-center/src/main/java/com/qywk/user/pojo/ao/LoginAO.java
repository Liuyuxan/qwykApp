package com.qywk.user.pojo.ao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qlh
 * @date 2024/04/18 15:05
 * @description  登录相关
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginAO {
    private String userId;
    private String password;
}
