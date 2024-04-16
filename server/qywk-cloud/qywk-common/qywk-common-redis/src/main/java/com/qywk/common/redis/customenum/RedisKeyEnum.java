package com.qywk.common.redis.customenum;

/**
 * @author ricetofu
 * @date 2023/11/3
 * @description redis键枚举，每使用到一个新的redis键，应该在这里定义好统一前缀，方便后续维护和后台管理等
 */
public enum RedisKeyEnum {

    LOGIN_TOKENS("login-tokens:", "用户的登陆token信息"),
    AUTH("auth:", "用户的权限树模型"),
    LIMIT("limit:", "接口限流redis键"),
    LIMIT_USER_LOGIN("limit-user-login:", "登陆接口专用限流键"),
    COURSE("course:", "用户课表数据缓存"),
    TEACHER_SIGN("teacherSign:", "教师打卡数据缓存"),
    TEACHER_SIGN_SPORT("teacherSign-sport:", "运动场：教师打卡数据缓存"),
    INFO("info:", "用户个人信息缓存"),
    SCORE("score:", "用户的成绩信息缓存"),
    GRADE_SCORE("grade-score:", "用户的等级考试信息缓存"),
    BANNER("banner:", "首页banner缓存"),
    USER_ICONS("user-icons:", "用户的icon信息缓存"),
    ARRANGEMENT("arrangement:", "考试安排信息缓存"),
    MAKEUP_ARRANGEMENT("makeup-arrangement:", "用户的补考信息缓存"),
    USER_SCHEDULE("user-schedule:", "用户的日程安排缓存"),
    SCHOOL_SYSTEMS_STATUS("schoolSystemsStatus:","学校有关的系统如官网、学工管理系统的系统状态缓存"),
    LOST_FOUND_IMG("lost-found-img:", "失误招领的图片缓存"),
    LOST_FOUND_ARTICLE("lost-found-article:", "失误招领文章缓存"),
    LOST_FOUND_COMMENT("lost-found-comment:", "失物招领评论区信息缓存"),
    WECHAT_STEP("wechat-step:", "微信记步信息缓存"),
    ACCESS_TOKEN("access_token:","微信消息推送的请求token参数"),


    // 以下是社区功能的redis-key
    COMMUNITY_COMMENT("community_comment:", "社区评论信息"),
    COMMUNITY_UPVOTE("community_upvote:", "点赞信息"),
    COMMUNITY_USER_INFO("community_user_info:", "社区用户信息"),
    COMMUNITY_USER_SETTING("community_user_setting", "社区用户设置")
    ;
    public final String prefix; // redis统一前缀
    public final String details; // redis键功能描述

    RedisKeyEnum(String prefix, String details) {
        this.prefix = prefix;
        this.details = details;
    }

    /**
     * 生成一个redis键
     * */
    public String create(String value) {
        return prefix + value;
    }

}
