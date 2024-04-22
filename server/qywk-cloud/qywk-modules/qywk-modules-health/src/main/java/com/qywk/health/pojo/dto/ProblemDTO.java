package com.qywk.health.pojo.dto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/**
 * @author qlh
 * @date 2024/03/21 8:08
 * @description 中医检测问题集
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("health_problem")
public class ProblemDTO {
    @TableId(type = IdType.AUTO)
    private String id;
    @TableField("problem")
    private String problem;
    @TableField("option_box")
    private String optionBox;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(value = "enable", fill = FieldFill.INSERT)
    private String enable;
}
