package com.qywk.health.pojo.dto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author qlh
 * @date 2024/04/22 23:08
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("plant")
public class PlantDTO {
    @TableId(type = IdType.AUTO)
    private String id;

    @TableField("name")
    private String name;

    @TableField("files")
    private String files;

    @TableField("description")
    private String description;

    @TableField("tag")
    private String tag;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "enable", fill = FieldFill.INSERT)
    private String enable;
}
