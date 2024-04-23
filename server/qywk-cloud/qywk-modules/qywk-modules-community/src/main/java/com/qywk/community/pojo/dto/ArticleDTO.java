package com.qywk.community.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author ricetofu
 * @date 2024/4/2
 * @description 文章数据库实体字段对应类
 */
@Data
@TableName("article")
public class ArticleDTO {

    /**
     * 文章的唯一id
     * */
    @TableId("article_id")
    private String articleId;

    /**
     * 文章的获赞数量
     * */
    @TableField("likes")
    private Integer likes;

    /**
     * 文章的发布者id
     * */
    @TableField("user_id")
    private String userId;

    /**
     * 文章的内容本体
     * */
    @TableField("content")
    private String content;

    /**
     * 文章的附件，文件服务器文件名格式，使用英文分号隔开多个文件
     * */
    @TableField("files")
    private String files;

    /**
     * 文章的状态码
     * */
    @TableField("state")
    private Integer state;

    /**
     * 文章的发布时间
     * */
    @TableField("publish_time")
    private LocalDateTime publishTime;

    /**
     * 文章的最近一次状态更新的时间
     * */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 进行文章的最近一次更新的用户的id
     * */
    @TableField("update_user")
    private String updateUser;

    /**
     * 文章的最近一次更新的描述性信息
     * */
    @TableField("update_details")
    private String updateDetails;

}
