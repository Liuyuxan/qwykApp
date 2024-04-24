package com.qywk.health.pojo.ao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * @author qlh
 * @date 2024/04/23 21:12
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanAO {
    private String planId;
    private String plantId;
    private String punchCycle;
    private Integer punchSize;
    private String remindTime;
    private String remindMusic;
    private String automatic;
    private String startTime;
    private String endTime;
}
