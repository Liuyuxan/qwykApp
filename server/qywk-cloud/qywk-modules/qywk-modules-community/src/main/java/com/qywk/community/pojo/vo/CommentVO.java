package com.qywk.community.pojo.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author qlh
 * @date 2024/03/26 16:26
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {
    // 评论id
    private String id;

    // 发布这条评论的用户的id
    private String userId;
    private String userName;
    private String userProfile;

    // 评论的内容
    private String content;

    // 评论的附件
    private String files;

    // 这条评论所属的文章的id
    private String articleId;

    // 回复id，即父评论的id
    private String replyId;
    private String replyName;

    // 楼主id
    private String floorId ;

    // 点赞量 默认0
    private Integer likes = 0;

    private Integer leafSize = 0;

    // 评论发布的时间
    private Timestamp publishTime;
}
