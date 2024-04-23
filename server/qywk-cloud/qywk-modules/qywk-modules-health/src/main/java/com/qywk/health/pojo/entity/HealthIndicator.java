package com.qywk.health.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * @author qlh
 * @date 2024/03/25 17:49
 * @description 健康解析json数据格式
 */
public class HealthIndicator{
    /**
     * 指标对象
     */
    private Indicator[] indicators;
    /**
     * 平均分
     */
    private int avg;
    /**
     * 建议
     */
    private String suggest;
}


