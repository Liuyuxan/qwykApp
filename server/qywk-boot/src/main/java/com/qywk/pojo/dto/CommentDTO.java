package com.qywk.pojo.dto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author QiLinHu
 * @date 2024/02/29 21:57
 * @description 评论实体
 */
@Data
@TableName("comment_info")
public class CommentDTO {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 账号
     */
    @TableField("username")
    private String username;

    /**
     * 描述信息
     */
    @TableField("description")
    private String description;

    /**
     * 图片集
     */
    @TableField("photos")
    private String photosJson;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否激活（举报中2/激活1/删除0/待审核-1/封禁-2）
     */
    @TableField(value = "enable")
    private String enable;
}
