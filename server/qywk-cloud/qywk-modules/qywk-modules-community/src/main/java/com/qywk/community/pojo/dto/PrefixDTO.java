package com.qywk.community.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ricetofu
 * @date 2024/4/5
 * @description 用户头衔信息数据库字段对应类
 */
@TableName("prefix")
@Data
public class PrefixDTO {

    /**
     * 头衔的唯一id
     * */
    @TableId("id")
    private Integer id;

    /**
     * 头衔的显示名称
     * */
    @TableField("display_name")
    private String displayName;

    /**
     * 头衔的描述性信息
     * */
    @TableField("details")
    private String details;

    /**
     * 头衔的前端样式信息
     * */
    @TableField("css")
    private String css;

}
