package com.qywk.health.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * @author qlh
 * @date 2024/03/25 17:49
 * @description 健康ai解析json数据格式
 */
public class Indicator {
    /**
     * 指标
     */
    private String info;
    /**
     * 分数
     */
    private int score;
}