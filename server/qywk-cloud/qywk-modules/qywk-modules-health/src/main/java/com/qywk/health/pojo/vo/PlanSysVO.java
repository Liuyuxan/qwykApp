package com.qywk.health.pojo.vo;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author qlh
 * @date 2024/04/23 21:55
 * @description
 */
@Data
public class PlanSysVO {
    private String id;

    private String plantId;

    private String name;

    private String subarea;

    private String punchCycle;

    private String punchCycleInfo;

    private Integer punchSize;

    private String remindTime;

    private String remindMusic;

    private String automatic;

    private String startTime;

    private String endTime;
}
