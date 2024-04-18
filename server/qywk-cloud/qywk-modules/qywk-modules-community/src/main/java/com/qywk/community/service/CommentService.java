package com.qywk.community.service;


import com.qywk.common.core.entity.ResultBody;
import com.qywk.community.pojo.ao.CommentAO;

/**
 * @author qlh
 * @date 2024/03/26 8:53
 * @description 评论功能
 */
public interface CommentService {
    /**
     * 通过文章id查询文章的评论信息
     * @param userId    用户id
     * @param articleId 文章id
     * @param page      页码
     * @param size      页数
     * @return
     */
    ResultBody queryFloor(String userId, String articleId, Integer page, Integer size);

    /**
     * 通过文章id和楼主id查询文章的二级评论信息
     * @param userId
     * @param articleId
     * @param floorId
     * @param page
     * @param size
     * @return
     */
    ResultBody queryLeaf(String userId, String articleId, String floorId, Integer page, Integer size);

    /**
     * 发布评论信息
     * @param userId
     * @param ao
     * @return
     */
    ResultBody send(String userId, CommentAO ao);

    /**
     * 评论删除
     * @param userId
     * @param articleId
     * @param id
     * @return
     */
    ResultBody delete(String userId, String articleId, String id);

    /**
     * 举报评论信息
     * @param userId
     * @param articleId
     * @param id
     * @return
     */
    ResultBody report(String userId, String articleId, String id);
}
