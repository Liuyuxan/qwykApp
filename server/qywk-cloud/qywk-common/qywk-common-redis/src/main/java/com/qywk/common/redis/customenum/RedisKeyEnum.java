package com.qywk.common.redis.customenum;

/**
 * @author ricetofu
 * @date 2023/11/3
 * @description redis键枚举，每使用到一个新的redis键，应该在这里定义好统一前缀，方便后续维护和后台管理等
 */
public enum RedisKeyEnum {

    // 用户相关
    LOGIN_TOKENS("login-tokens:", "用户的登陆token信息"),
    AUTH("auth:", "用户的权限树模型"),
    LIMIT("limit:", "接口限流redis键"),
    LIMIT_USER_LOGIN("limit-user-login:", "登陆接口专用限流键"),
    REGISTER_TEL_CODE("register-tel-code:", "注册电话验证码"),

    // 微信相关
    WECHAT_STEP("wechat-step:", "微信记步信息缓存"),
    ACCESS_TOKEN("access_token:","微信消息推送的请求token参数"),

    // 健康植物模块相关
    PLANT_INFO("plant-info:", "植物信息缓存"),
    PLANT_USER("plant-user", "用户与拥有的植物关联表缓存"),

    // 计划相关
    PLAN_PUNCH("plan_punch:", "计划打卡缓存"),

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
