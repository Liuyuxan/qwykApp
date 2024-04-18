package com.qywk.community.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@TableName("comment")
public class CommentDTO {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    // 评论的唯一id
    private String id;

    @TableField("user_id")
    // 发布这条评论的用户的id
    private String userId;

    @TableField("content")
    // 评论的内容
    private String content;

    @TableField("files")
    // 评论的附件
    private String files;

    @TableField("article_id")
    // 这条评论所属的文章的id
    private String articleId;

    @TableField("reply_id")
    // 回复id，即父评论的用户id
    private String replyId;

    @TableField("floor_id")
    // 楼主id
    private String floorId;

    @TableField("likes")
    // 点赞量 默认0
    private Integer likes = 0;

    @TableField("heat")
    private Integer heat;

    @TableField("leaf_size")
    private Integer leafSize;

    @TableField("publish_time")
    // 评论发布的时间
    private Timestamp publishTime = new Timestamp(System.currentTimeMillis());

    @TableField("delete_time")
    // 如果该评论被删除了，删除时间
    private LocalDateTime deleteTime;

    @TableField("delete_user")
    // 删除这条评论的用户id
    private String deleteUser;

    @TableField("delete_type")
    // 该评论被删除的类型（管理员删除，自己删除，随父评论删除等）
    private String deleteType;

    @TableField("delete_details")
    // 删除描述，如为管理员删除，则会有评论删除的原因描述
    private String deleteDetails;

    @TableField("enable")
    // 激活状态
    private String enable;


}
