package com.qywk.community.service;

import com.qywk.common.core.entity.ResultBody;

/**
 * @author qlh
 * @date 2024/03/26 8:54
 * @description 点赞功能
 */
public interface UpvoteService {

    /**
     * 查询用户对该键对象是否点赞
     * @param userId    用户id
     * @param keyword   键id
     * @param type      键类型
     * @return  status : true/false
     */
    boolean queryLikeStatus(String userId, String keyword, String type);

    ResultBody button(String userId, String keyword, String type);
}
