package com.qywk.user.pojo.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

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
     * 用户经验值
     */
    private Integer exp;

    /**
     * 用户等级
     */
    private Integer grade;

    /**
     * 货币
     */
    private Integer currency;

    /**
     * 是否激活
     * */
    private String enable;
}
