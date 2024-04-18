package com.qywk.user.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author qlh
 * @date 2024/04/18 15:18
 * @description  用户信息
 */
@Data
@TableName("user_info")
public class UserInfoDTO {

    /**
     * 用户名
     * */
    @TableId("user_id")
    private String userId;

    /**
     * 用户密码
     * */
    @TableField("password")
    private String password;

    /**
     * 用户的昵称
     * */
    @TableField("nickname")
    private String nickname;

    /**
     * 用户的微信授权码
     * */
    @TableField("open_id")
    private String openId;

    /**
     * 用户的微信会话码
     * */
    @TableField("session_id")
    private String sessionId;

    /**
     * 电话
     * */
    @TableField("tel")
    private String tel;

    /**
     * 邮箱
     * */
    @TableField("email")
    private String email;

    /**
     * 用户的头像图片名
     * */
    @TableField("profile")
    private String profile;

    /**
     * 创建时间
     * */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     * */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 是否激活
     * */
    @TableField("enable")
    private String enable;
}

