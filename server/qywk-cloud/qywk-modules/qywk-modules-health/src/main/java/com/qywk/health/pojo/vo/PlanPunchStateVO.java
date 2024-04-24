package com.qywk.health.pojo.vo;

import lombok.Data;

/**
 * @author qlh
 * @date 2024/04/23 22:50
 * @description
 */
@Data
public class PlanPunchStateVO {
    private String id;

    private String plantId;

    private String name;

    private String subarea;

    private String punchCycle;

    private String punchCycleInfo;

    private boolean finish;
}
