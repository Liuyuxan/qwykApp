package com.qywk.health.pojo.dto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author qlh
 * @date 2024/04/23 11:08
 * @description 问卷结果实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("questionnaire_result")
public class QuestionnaireResultDTO {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField("user_id") // 用户id
    private String userId;

    @TableField("health_indicator_json")
    private String json;


    @TableField(value = "enable", fill = FieldFill.INSERT) // 激活状态
    private String enable;

    @TableField(value = "create_time", fill = FieldFill.INSERT) // 创建时间
    private LocalDateTime createTime;
}
