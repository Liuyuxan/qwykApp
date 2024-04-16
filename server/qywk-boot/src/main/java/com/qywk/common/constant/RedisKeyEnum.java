package com.qywk.common.constant;

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
    INFO("info:", "用户个人信息缓存"),
    BANNER("banner:", "首页banner缓存"),
    USER_ICONS("user-icons:", "用户的icon信息缓存"),
    WECHAT_STEP("wechat-step:", "微信记步信息缓存"),
    ACCESS_TOKEN("access_token:","微信消息推送的请求token参数"),
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
