package com.qywk.pojo.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.qywk.common.constant.EnableConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author qlh
 * @date 2024/04/16 15:34
 * @description
 */

@TableName("plant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantDTO {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    @TableField("name")
    private String name;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField("enable")
    private String enable = EnableConstants.ACTIVATION;
}
