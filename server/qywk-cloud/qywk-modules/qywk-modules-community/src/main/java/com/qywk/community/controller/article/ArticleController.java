package com.qywk.community.controller.article;

import com.qywk.common.core.entity.ResultBody;
import com.qywk.community.pojo.ao.ArticleQueryAO;
import org.springframework.web.bind.annotation.*;

/**
 * @author ricetofu
 * @date 2024/3/30
 * @description 与用户文章相关的一些接口，这个接口涵盖了用户对文章的各种操作，不包括管理员相关接口功能!
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    /**
     * 进行文章查询
     * @param ao 查询条件封装类
     * */
    @PostMapping("/query")
    public ResultBody query(@RequestBody ArticleQueryAO ao) {


        return null;
    }

    /**
     * 获取指定用户发布过的文章
     * @param ao 查询条件封装
     * */
    @PostMapping("/query/user/{user_id}")
    public ResultBody queryByUserId(@PathVariable("user_id") String user_id, @RequestBody ArticleQueryAO ao) {


        return null;
    }

    /**
     * 获取用户的关注所发布的文章
     * */
    @PostMapping("/query/follow")
    public ResultBody queryByUserFollow() {



        return null;
    }

    /**
     * 获取指定tag标签下的文章
     * */
    @PostMapping("/query/tag/{tag_id}")
    public ResultBody queryByTag(@PathVariable("tag_id") Integer tag_id, @RequestBody ArticleQueryAO ao) {



        return null;
    }

    /**
     * 获取一篇文章的详细内容，根据文章的id
     * */
    @GetMapping("/content/{article_id}")
    public ResultBody getByArticle_id(@PathVariable("article_id") String article_id) {


        return null;
    }

    /**
     * 用户发布文章
     * */
    @PostMapping("/publish")
    public ResultBody publishArticle() {


        return null;
    }

    /**
     * 用户删除一篇文章，只用于用户删除自己发布的文章
     * */
    @PostMapping("/delete/{article_id}")
    public ResultBody deleteArticle(@PathVariable("article_id") String article_id) {


        return null;
    }

    /**
     * 对一个文章进行喜欢操作
     * */
    @GetMapping("/like/{article_id}")
    public ResultBody like(@PathVariable("article_id") String article_id) {

        return null;
    }

    /**
     * 取消对一个文章的喜欢操作
     * */
    @GetMapping("/cancelLike/{article_id}")
    public ResultBody cancelLike(@PathVariable("article_id") String article_id) {

        return null;
    }

    /**
     * 对文章进行一次举报操作
     * */
    @PostMapping("/report")
    public ResultBody report() {


        return null;
    }

}
