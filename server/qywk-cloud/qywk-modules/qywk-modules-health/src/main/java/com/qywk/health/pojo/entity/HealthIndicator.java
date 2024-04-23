package com.qywk.health.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
     * 体质
     */
    private List<String> constitution;

    /**
     * 平均分
     */
    private int avg;

    /**
     * Compare information
     */
    private String compareInfo;

    /**
     * 建议
     */
    private String suggest;
}


