package com.qywk.community.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author ricetofu
 * @date 2024/4/5
 * @description 用户信息数据的封装返回类
 */
@Data
public class UserVO {

    /**
     * 用户的id
     * */
    private String userId;

    /**
     * 用户的昵称
     * */
    private String nickname;

    /**
     * 用户的经验量
     * */
    private Long exp;

    /**
     * 用户的粉丝数量
     * */
    private Integer fun;

    /**
     * 用户的关注数量
     * */
    private Integer follow;

    /**
     * 用户喜欢的文章数量
     * */
    private Integer like;

    /**
     * 用户的获赞数量
     * */
    private Integer reward;

    /**
     * 用户的头衔列表
     * */
    private List<PrefixVO> prefixes;

}
