package com.qywk.community.service.impl;

import com.qywk.common.core.constant.EnableConstants;
import com.qywk.common.core.entity.ResultBody;
import com.qywk.common.core.utils.PageUtils;
import com.qywk.common.core.utils.SensitiveWordFilter;
import com.qywk.common.redis.customenum.RedisKeyEnum;
import com.qywk.common.redis.service.RedisService;
import com.qywk.community.mapper.CommentMapper;
import com.qywk.community.pojo.ao.CommentAO;
import com.qywk.community.pojo.dto.CommentDTO;
import com.qywk.community.pojo.vo.CommentVO;
import com.qywk.community.service.ArticleService;
import com.qywk.community.service.CommentService;

import com.qywk.community.utils.CommentUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qlh
 * @date 2024/03/26 8:53
 * @description 评论功能
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    RedisService redisService;
    @Autowired
    ArticleService articleService;
//    @Autowired
//    UserService userService;

    // 社区用户缓存键
    private static final String USER_PREFIX = RedisKeyEnum.COMMUNITY_USER_INFO.prefix;
    // 社区评论缓存键
    private static final String COMMENT_PREFIX = RedisKeyEnum.COMMUNITY_COMMENT.prefix;
    // 评论树缓存键
    private static final String FLOOR = "floor:";
    // 二级叶子缓存键
    private static final String LEAF = "leaf:";

    /**
     * 通过文章id查询文章的评论信息
     * @param userId    用户id
     * @param articleId 文章id
     * @param page      页码
     * @param size      页数
     * @return
     */
    @Override
    public ResultBody queryFloor(String userId, String articleId, Integer page, Integer size) {
        // 文章鉴权,判断是否有查看文章的评论权限（主要是判断文章是否被封禁）
        if(!articleService.articleEnableState(articleId)){
            return ResultBody.error().message("文章已被封禁");
        }
        // 按照100来 9舍取整百 根据页数和每页大小计算偏移量
        int offset = ((page - 1) * size) / 100 * 100;
        // comment -> article -> floor -> offset 缓存key文件结构
        String key = COMMENT_PREFIX + articleId + ":" + FLOOR + offset;
        List<CommentVO> floor = null;
        if(redisService.hasKey(key)){
            floor = redisService.getCacheObject(key);
        }else {
            List<CommentDTO> list = commentMapper.selectByFloor(articleId, offset);
            if(list == null || list.isEmpty()) return ResultBody.error().message("暂无评论");
            floor = new ArrayList<>();
            for (CommentDTO e : list) {
                CommentVO comment = new CommentVO();
                BeanUtils.copyProperties(e, comment);
                floor.add(comment);
            }
            redisService.setCacheObject(key, floor, 1L, TimeUnit.HOURS);
        }

        // 分页处理
        // 调整页数，使其匹配提前分页的偏移量
        int adjustedPage = (page * size - offset) / size;
        PageUtils<CommentVO> pageUtils = new PageUtils<>(floor, adjustedPage, size);
        Integer total = commentMapper.selectByArticleIdCount(articleId);
        List<CommentVO> records = pageUtils.getRecords();
        return ResultBody.ok().data("total", total).data("records", records);
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
    @Override
    public ResultBody queryLeaf(String userId, String articleId, String floorId, Integer page, Integer size) {
        // 文章鉴权,判断是否有查看文章的评论权限（主要是判断文章是否被封禁）
        if(!articleService.articleEnableState(articleId)){
            return ResultBody.error().message("文章已被封禁");
        }
        // 按照100来 9舍取整百 根据页数和每页大小计算偏移量
        int offset = ((page - 1) * size) / 100 * 100;
        // comment -> article -> leaf -> offset 缓存key文件结构
        String key = COMMENT_PREFIX + articleId + ":" + LEAF + offset;
        List<CommentVO> leaf = null;
        if(redisService.hasKey(key)){
            leaf = redisService.getCacheObject(key);
        }else {
            List<CommentDTO> list = commentMapper.selectByLeaf(floorId, offset);
            if(list == null || list.isEmpty()) return ResultBody.error().message("暂无评论");
            leaf = new ArrayList<>();
            for (CommentDTO e : list) {
                CommentVO comment = new CommentVO();
                BeanUtils.copyProperties(e, comment);
                leaf.add(comment);
            }
            redisService.setCacheObject(key, leaf, 1L, TimeUnit.HOURS);
        }

        // 分页处理
        // 调整页数，使其匹配提前分页的偏移量
        int adjustedPage = (page * size - offset) / size;
        PageUtils<CommentVO> pageUtils = new PageUtils<>(leaf, adjustedPage, size);
        List<CommentVO> records = pageUtils.getRecords();
        return ResultBody.ok().data("records", records);
    }

    /**
     * 发布评论信息
     * @param userId
     * @param ao
     * @return
     */
    @Override
    public ResultBody send(String userId, CommentAO ao) {
        // 文章鉴权,判断是否有查看文章的评论权限（主要是判断文章是否被封禁）
        if (!articleService.articleEnableState(ao.getArticleId())) {
            return ResultBody.error().message("文章已被封禁");
        }
        // 敏感词过滤
        boolean isFilter = SensitiveWordFilter.isContains(ao.getContent());

        if (isFilter) {
            // 敏感词过滤
            ao.setContent(SensitiveWordFilter.filterReplace(ao.getContent()));
        }

        CommentDTO comment = new CommentDTO();
        BeanUtils.copyProperties(ao, comment);
        comment.setUserId(userId);
        comment.setEnable(EnableConstants.ACTIVATION);
        commentMapper.insert(comment);
        // 添加@功能
        notifyMentionedUsers(userId, comment.getArticleId(), extractUserId(comment.getContent()));

        // 更新缓存
        updateCacheOnComment(ao.getArticleId(), ao.getFloorId());

        if (isFilter) {
            return ResultBody.ok().message("发表成功，请注意友善发言");
        }
        return ResultBody.ok().message("发布成功");
    }

    // 更新缓存方法
    private void updateCacheOnComment(String articleId, String floorId) {
        // 根据评论类型更新对应缓存
        if (CommentUtils.isFloor(floorId)) {
            // 更新楼层评论缓存
            // 例如：根据文章 ID 更新对应的楼层评论缓存
            String key = COMMENT_PREFIX + articleId + ":" + FLOOR;
            redisService.deleteCollectionKeys(key);
        } else {
            // 更新叶子评论缓存
            // 例如：根据文章 ID 更新对应的叶子评论缓存
            String key = COMMENT_PREFIX + articleId + ":" + LEAF;
            redisService.deleteCollectionKeys(key); // 删除缓存
        }
    }


    /**
     * 评论删除
     * @param userId
     * @param articleId
     * @param id
     * @return
     */
    @Override
    public ResultBody delete(String userId, String articleId, String id) {
        // todo 用户权限鉴权判断用户是否有删除权限 先mock的数据
        CommentDTO dto = commentMapper.selectById(id);
        dto.setEnable(EnableConstants.DELETE);
        dto.setDeleteTime(LocalDateTime.now());
        dto.setDeleteUser(userId);
        dto.setDeleteType("个人");
        dto.setDeleteDetails("个人删除");
        // 删除评论
        commentMapper.updateById(dto);
        // 清除缓存
        clearCacheComment(articleId);

        return ResultBody.ok().message("删除成功");
    }

    // 清除缓存方法
    private void clearCacheComment(String articleId) {
        // 清除文章相关的缓存
        // 例如：根据文章 ID 清除对应的楼层评论缓存和叶子评论缓存
        String key = COMMENT_PREFIX + articleId + ":";
        redisService.deleteCollectionKeys(key);
    }

    /**
     * 举报评论信息
     * @param userId
     * @param articleId
     * @param id
     * @return
     */
    @Override
    public ResultBody report(String userId, String articleId, String id) {
        return ResultBody.ok().message("举报成功");
    }

    /**
     * 从评论内容中提取被@的用户
     * @param content
     * @return  {k, v} k: userId, v: username
     */
    private Map<String, String> extractUserId(String content) {
        Map<String, String>  users = new HashMap<>();
        Pattern pattern = Pattern.compile("&@(.*?)\\|(\\d+)&");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            // userId -> username
            users.put(matcher.group(2), matcher.group(1));
        }
        return users;
    }

    /**
     * 发送通知给被@用户
     * @param userId
     * @param articleId
     * @param users
     */
    private void notifyMentionedUsers(String userId, String articleId, Map<String, String> users) {
        // todo 回推待定
        String content = "提到了你：@" + userId;
        for (String id : users.keySet()){

        }
    }

    /**
     * 同步评论中出现被@的用户的用户昵称
     * @param content
     */
    private void SyncMentionedUsernames(String content){

    }
    /**
     * 热度算法，时间越久衰减越多
     * @param x
     * @param y
     * @return
     */
    private int heatCmp(CommentVO x, CommentVO y) {
        double heatX = calculateHeat(x);
        double heatY = calculateHeat(y);
        return heatX > heatY ? -1 : 1;
    }

    private double calculateHeat(CommentVO comment) {
        double likesWeight = 0.6;
        double sizeWeight = 0.3;
        double timeWeight = 0.1;

        double heat = likesWeight * comment.getLikes() +
                sizeWeight * comment.getLeafSize() +
                timeWeight * calculateTimeFactor(comment.getPublishTime().getTime());

        return heat;
    }

    private double calculateTimeFactor(Long publishTime) {
        // 假设当前时间为 System.currentTimeMillis()
        long currentTime = System.currentTimeMillis();

        // 计算时间间隔，假设以分钟为单位
        long timeDiff = (currentTime - publishTime) / 1000 / 60;

        // 使用指数衰减函数
        return Math.pow(0.9, timeDiff);
    }
}