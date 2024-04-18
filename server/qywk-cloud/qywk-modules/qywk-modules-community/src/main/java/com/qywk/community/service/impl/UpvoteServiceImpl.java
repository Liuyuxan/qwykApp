package com.qywk.community.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qywk.common.core.entity.ResultBody;
import com.qywk.common.redis.customenum.RedisKeyEnum;
import com.qywk.common.redis.service.RedisService;
import com.qywk.community.mapper.UpvoteMapper;
import com.qywk.community.pojo.dto.UpvoteDTO;
import com.qywk.community.service.UpvoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


/**
 * @author qlh
 * @date 2024/03/26 8:54
 * @description 点赞功能
 */
@Service
public class UpvoteServiceImpl implements UpvoteService {
    @Autowired
    RedisService redisService;
    @Autowired
    UpvoteMapper upvoteMapper;

    private static final String UPVOTE_PREFIX = RedisKeyEnum.COMMUNITY_UPVOTE.prefix;

    /**
     * 查询用户对该键对象是否点赞
     * @param userId    用户id
     * @param keyword        键id
     * @param type      键类型
     * @return  status : true/false
     */
    @Override
    public boolean queryLikeStatus(String userId, String keyword, String type) {
        // 先将 keyword 进行 hash 分块
        int hashcode = userId.hashCode() % 33;
        String key = UPVOTE_PREFIX + keyword + ":" + type + ":" + hashcode;

        Set ids = null;
        if(redisService.hasKey(key)){
            ids = redisService.getCacheObject(key);
        }else {
            QueryWrapper<UpvoteDTO> wrapper = new QueryWrapper<>();
            wrapper.eq("keyword", keyword)
                    .eq("type", type)
                    .eq("hashcode", hashcode);
            UpvoteDTO upvote = upvoteMapper.selectOne(wrapper);
            String setJson = null;
            if(upvote != null) setJson = upvote.getSetJson();
            if(setJson == null || setJson.isEmpty()) return false;
            ids = JSONObject.parseObject(setJson, Set.class);
        }
        return ids.contains(userId);
    }

    @Override
    public ResultBody button(String userId, String keyword, String type) {
        int hashcode = userId.hashCode() % 33;
        String key = UPVOTE_PREFIX + keyword + ":" + type + ":" + hashcode;
        Set ids = null;
        if(redisService.hasKey(key)){
            ids = redisService.getCacheObject(key);
        }else {
            QueryWrapper<UpvoteDTO> wrapper = new QueryWrapper<>();
            wrapper.eq("keyword", keyword)
                    .eq("type", type)
                    .eq("hashcode", hashcode);
            UpvoteDTO upvote = upvoteMapper.selectOne(wrapper);
            String setJson = null;
            if(upvote != null) setJson = upvote.getSetJson();
            if(setJson == null || setJson.isEmpty()){
                setJson = "[\"" + userId + "\"]";
                upvote = new UpvoteDTO(keyword, type, setJson, hashcode);
                upvoteMapper.insert(upvote);
                ids = new HashSet();
            }else {
                ids = JSONObject.parseObject(setJson, Set.class);
            }
        }
        if(ids.contains(userId)){
            ids.remove(userId);
            redisService.setCacheObject(key, ids);
            return ResultBody.ok().message("取消点赞成功").data("status", false);
        }else {
            ids.add(userId);
            redisService.setCacheObject(key, ids);
            return ResultBody.ok().message("点赞成功").data("status", true);
        }
    }
}
