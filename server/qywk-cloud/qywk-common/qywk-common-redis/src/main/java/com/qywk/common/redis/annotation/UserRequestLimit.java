package com.qywk.common.redis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ricetofu
 * @date 2023/10/22
 * @description 单用户接口限流，通过token里的user_id来唯一定位用户<br/>
 * 所以使用时请确保你的接口是需要登陆才能访问的接口<br/>
 * 不然会无法拿到用户的user_id标识导致限流失效<br/>
 * 请将这个注解用在controller方法上<br/>
 * 限流逻辑详见: com.zjyt.wxzx.common.redis.configure.RequestLimitInterceptor
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserRequestLimit {

    /**
     * 请求的次数限制
     * */
    int requestLimit();

    /**
     * 限制刷新的秒数
     * */
    int refreshSeconds();

}
