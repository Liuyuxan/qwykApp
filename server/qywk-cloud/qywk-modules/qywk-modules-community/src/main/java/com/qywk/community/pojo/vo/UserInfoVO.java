package com.qywk.community.pojo.vo;

import lombok.Data;

/**
 * @author qlh
 * @date 2024/04/18 21:56
 * @description
 */
@Data
public class UserInfoVO {
    /**
     * 用户名
     * */
    private String userId;

    /**
     * 用户的昵称
     * */
    private String nickname;

    /**
     * 电话
     * */
    private String tel;

    /**
     * 邮箱
     * */
    private String email;

    /**
     * 用户的头像图片名
     * */
    private String profile;

    /**
     * 是否激活
     * */
    private String enable;
}
