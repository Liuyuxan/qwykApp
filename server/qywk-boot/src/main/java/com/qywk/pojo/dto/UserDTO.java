package com.qywk.pojo.dto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author QiLinHu
 * @date 2024/02/29 23:02
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_info")
public class UserDTO {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 用户昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 账号
     */
    @TableField("user_id")
    private String userId;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 微信open
     */
    @TableField("open_id")
    private String openId;

    /**
     * 微信session
     */
    @TableField("session_id")
    private String sessionId;

    /**
     * 手机号
     */
    @TableField("phone_num")
    private String phoneNum;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;
    /**
     * 头像
     */
    @TableField("profile")
    private String profile;

    /**
     * 发布时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 激活状态
     */
    @TableField(value = "enable", fill = FieldFill.INSERT)
    private String enable;
}
