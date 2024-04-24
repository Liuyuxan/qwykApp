package com.qywk.health.pojo.ao;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author qlh
 * @date 2024/04/23 21:51
 * @description
 */
@Data
public class PlanSysAO {
    private String plantId;
    private String name;
    private String subarea;
    private String punchCycle;
    private Integer punchSize;
    private String remindTime;
    private String remindMusic;
    private String automatic;
    private String startTime;
    private String endTime;
}
