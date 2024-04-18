package com.qywk.community.controller.article;

import com.qywk.common.core.entity.ResultBody;
import com.qywk.common.core.utils.SensitiveWordFilter;
import com.qywk.community.pojo.ao.CommentAO;
import com.qywk.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author qlh
 * @date 2024/03/26 8:53
 * @description 评论功能
 */
@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 通过文章id查询文章的一级评论信息
     * @param userId    用户id
     * @param articleId 文章id
     * @param page      页码
     * @param size      页数
     * @return
     */
    @GetMapping("/query/floor")
    public ResultBody queryFloor(@RequestHeader(value = "user_id") String userId,
                                 @RequestParam(value = "article_id") String articleId,
                                 @RequestParam(value = "page") Integer page,
                                 @RequestParam(value = "size") Integer size){
        return commentService.queryFloor(userId, articleId, page, size);
    }

    /**
     * 通过文章id和楼主id查询文章的二级评论信息
     * @param userId
     * @param articleId
     * @param floorId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/query/leaf")
    public ResultBody queryLeaf(@RequestHeader(value = "user_id") String userId,
                                @RequestParam(value = "article_id") String articleId,
                                @RequestParam(value = "floor_id") String floorId,
                                @RequestParam(value = "page") Integer page,
                                @RequestParam(value = "size") Integer size){
        return commentService.queryLeaf(userId, articleId, floorId, page, size);
    }


    /**
     * 发布评论信息
     * @param userId
     * @param ao
     * @return
     */
    @PostMapping("/send")
    public ResultBody send(@RequestHeader(value = "user_id") String userId,
                           @RequestBody CommentAO ao){
        return commentService.send(userId, ao);

    }

    /**
     * 评论删除
     * @param userId
     * @param articleId
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public ResultBody delete(@RequestHeader(value = "user_id") String userId,
                             @RequestParam(value = "article_id") String articleId,
                             @RequestParam(value = "comment_id") String id){
        return commentService.delete(userId, articleId, id);
    }

    /**
     * 举报评论信息
     * @param userId
     * @param articleId
     * @param id
     * @return
     */
    @PostMapping("/report")
    public ResultBody report(@RequestHeader(value = "user_id") String userId,
                             @RequestParam(value = "article_id") String articleId,
                             @RequestParam(value = "comment_id") String id){
        return commentService.report(userId, articleId, id);
    }

    @CrossOrigin("*")
    @GetMapping("/test")
    public ResultBody test(@RequestParam String content){
        return ResultBody.ok().data("content", SensitiveWordFilter.filterReplace(content));
    }


}
