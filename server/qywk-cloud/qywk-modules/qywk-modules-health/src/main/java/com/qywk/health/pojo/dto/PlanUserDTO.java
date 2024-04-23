package com.qywk.health.pojo.dto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDateTime;

/**
 * @author qlh
 * @date 2024/04/23 17:59
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("plan_user")
public class PlanUserDTO {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField("user_id")
    private String userId;

    @TableField("plan_id")
    private String planId;

    @TableField("plant_id")
    private String plantId;

    @TableField("name")
    private String name;

    @TableField("punch_cycle")
    private String punchCycle;

    @TableField("punch_size")
    private Integer punchSize;

    @TableField("subarea")
    private String subarea;

    @TableField("remind_time")
    private String remindTime;

    @TableField("remind_music")
    private String remindMusic;

    @TableField("automatic")
    private String automatic;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "enable", fill = FieldFill.INSERT)
    private String enable;
}
