package com.qywk.community.pojo.vo;

import lombok.Data;

/**
 * @author qlh
 * @date 2024/04/22 11:15
 * @description
 */
@Data
public class UserBasicInfoVO {
    /**
     * 用户名
     * */
    private String userId;

    /**
     * 用户的昵称
     * */
    private String nickname;

    /**
     * 用户的头像图片名
     * */
    private String profile;

    /**
     * 是否激活
     * */
    private String enable;
}
