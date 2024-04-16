package com.qywk.common.customenum;

/**
 * @author lwm
 * @date 2020-09-12 17:05
 * @description 异常返回配置类
 */
public enum CodeStateEnum {

    /**
     * 业务状态码 1xx（格式： key值）
     */
    SERVER_NULL_DATA(101,"当前暂无数据或查询信息并不存在"),
    SERVER_ERROR_PARAM(102,"参数有误"),
    SERVER_PARAM_NOTNULL(103,"参数不能为空"),
    /**
     * 成功状态码 2xx
     */
    SUCCESS(200, "请求成功"),

    /**
     * 客户端错误状态码 4xx
     */
    AUTH_UNAUTHORIZED(401, "权限模块：用户未授权"),
    AUTH_FORBIDDEN(402, "权限模块：令牌已过期或验证不正确!"),
    AUTH_TOKEN_NOT_NULL(403, "权限模块：令牌不能为空"),
    AUTH_LOGIN_EXPIRE(404, "权限模块：登录状态已过期，请重新登录"),
    AUTH_TOKEN_FAIL(405, "权限模块：令牌验证失败，请尝试重新登录"),
    AUTH_FLUSH_FAIL(406,"权限模块: 权限刷新失败，请重新登录"),
    AUTH_NO_PERMISSION(407,"权限模块: 没有接口的访问权限!"),
    AUTH_REQUEST_LIMIT(408,"接口限流: 收到请求过多!请稍后再试!"),

    /**
     * 登录错误状态码 3xx
     */
    LOGIN_USER_NOT_NULL(301, "用户名不存在"),
    LOGIN_PASSWORD_FAIL(302, "密码错误"),
    LOGIN_USER_TO_REGISTER(303, "用户以注册使用，请勿重复注册"),

    /**
     * 服务器错误状态码 5xx
     */
    ERROR(500, "系统内部错误"),

    /**
     * redis相关状态码 6xx
     */

    /*微信解码异常*/
    ERROR_NOT_INFO(60000, "错误，没有解码数据"),
    ERROR_DECODE(60001, "解码失败: 参数已失效!");


    public final Integer code;
    public final String message;

    CodeStateEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
