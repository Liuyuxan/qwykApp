package com.qywk.health.pojo.dto;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author qlh
 * @date 2024/04/22 23:20
 * @description 用户与植物关联表
 */

@Data
@TableName("plant_user")
public class PlantUserDTO {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField("user_id") // 用户id
    private String userId;

    @TableField("plant_id") // 植物中药id
    private String plantId;

    @TableField("exp") // 经验值
    private Integer exp;

    @TableField(value = "enable", fill = FieldFill.INSERT) // 激活状态
    private String enable;

    @TableField(value = "create_time", fill = FieldFill.INSERT) // 创建时间
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE) // 修改时间
    private LocalDateTime updateTime;
}
