package com.qywk.community.service;

/**
 * @author qlh
 * @date 2024/03/26 8:52
 * @description 文章功能
 */
public interface ArticleService {
    /**
     * 文章鉴权,判断是否有查看文章的评论权限（主要是判断文章是否被封禁）
     * @param articleId
     * @return
     */
    boolean articleEnableState(String articleId);
}
