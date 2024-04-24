package com.qywk.health.pojo.dto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDateTime;

/**
 * @author qlh
 * @date 2024/04/23 18:00
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("plan_sys")
public class PlanSysDTO {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField("plant_id")
    private String plantId;

    @TableField("user_id")
    private String userId;

    @TableField("name")
    private String name;

    @TableField("subarea")
    private String subarea;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "enable", fill = FieldFill.INSERT)
    private String enable;

    @TableField("punch_cycle")
    private String punchCycle;

    @TableField("punch_size")
    private Integer punchSize;

    @TableField("remind_time")
    private String remindTime;

    @TableField("remind_music")
    private String remindMusic;

    @TableField("automatic")
    private String automatic;

    @TableField("start_time")
    private String startTime;

    @TableField("end_time")
    private String endTime;
}
