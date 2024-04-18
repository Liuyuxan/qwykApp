package com.qywk.community.controller.article;

import com.qywk.common.core.entity.ResultBody;
import com.qywk.community.service.UpvoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author qlh
 * @date 2024/04/08 23:39
 * @description
 */
@RestController
@RequestMapping("/upvote")
public class UpvoteController {

    @Autowired
    UpvoteService upvoteService;

    /**
     * 查询用户对该键对象是否点赞
     * @param userId 用户id
     * @param keyword   查询的键id
     * @param type    键类型
     * @return  status : true/false
     */
    @GetMapping("/query/likeStatus")
    public ResultBody queryLikeStatus(@RequestHeader(value = "user_id") String userId,
                                      @RequestParam(value = "keyword") String keyword,
                                      @RequestParam(value = "type") String type){
        return ResultBody.ok().data("status", upvoteService.queryLikeStatus(userId, keyword, type));

    }

    @PostMapping("/like/button")
    public ResultBody button(@RequestHeader(value = "user_id") String userId,
                             @RequestParam(value = "keyword") String keyword,
                             @RequestParam(value = "type") String type){
        return upvoteService.button(userId, keyword, type);
    }
}
