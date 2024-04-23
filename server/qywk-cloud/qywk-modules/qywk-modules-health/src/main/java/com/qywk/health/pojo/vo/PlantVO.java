package com.qywk.health.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author qlh
 * @date 2024/04/23 0:19
 * @description
 */
@Data
public class PlantVO {
    private String id;

    private String name;

    private String files;

    private String description;

    private String tag;
    // 解锁状态
    private boolean unlockedStatus;
}
