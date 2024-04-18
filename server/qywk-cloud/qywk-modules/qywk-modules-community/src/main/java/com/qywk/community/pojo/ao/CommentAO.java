package com.qywk.community.pojo.ao;

import lombok.Data;

/**
 * @author qlh
 * @date 2024/03/26 16:25
 * @description
 */
@Data
public class CommentAO {
    // 评论的内容
    private String content;

    // 评论的附件
    private String files;

    // 这条评论所属的文章的id
    private String articleId;

    // 回复id，即父评论的id
    private String replyId;

    // 楼主id
    private String floorId;
}
