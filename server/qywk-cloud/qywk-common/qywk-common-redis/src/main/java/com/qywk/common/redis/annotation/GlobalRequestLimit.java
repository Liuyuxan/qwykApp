package com.qywk.common.redis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ricetofu
 * @date 2023/10/22
 * @description redis全局限流注解<br/>
 * 请将这个注解用在controller方法上<br/>
 * 限流逻辑详见: com.zjyt.wxzx.common.redis.configure.RequestLimitInterceptor
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GlobalRequestLimit {

    /**
     * 请求的次数限制
     * */
    int requestLimit();

    /**
     * 限制刷新的秒数
     * */
    int refreshSeconds();

}
