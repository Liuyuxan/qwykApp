package com.qywk.common.config.redis;

import com.alibaba.fastjson2.JSONObject;
import com.qywk.common.constant.CodeStateEnum;
import com.qywk.common.entity.ResultBody;
import com.qywk.common.redis.annotation.GlobalRequestLimit;
import com.qywk.common.redis.annotation.UserRequestLimit;
import com.qywk.common.redis.service.RedisService;
import com.qywk.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;
import com.qywk.common.constant.SecurityConstants;

/**
 * @author ricetofu
 * @date 2023/10/22
 * @description 接口限流拦截器
 */
@SpringBootConfiguration
@ConditionalOnClass(InterceptorRegistry.class) // 有些模块没有配置springboot，在某些模块上不启用
public class RequestLimitInterceptor implements HandlerInterceptor {
    // 日志对象
    Logger logger =  LoggerFactory.getLogger(RequestLimitInterceptor.class);

    // redis操作对象
    @Autowired
    private RedisService redisService;

    static {
        // 提示作用的注释，方便知道这个类是否被加载
        LoggerFactory.getLogger(RequestLimitInterceptor.class).info("本模块限流注解已开启");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            // 如果请求没有映射到一个方法上，那么跳过限流判断
            return true;
        }

        // 判断handlerMethod上有没有限流注解
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // 1.全局限流注解
        GlobalRequestLimit globalRequestLimit = handlerMethod.getMethodAnnotation(GlobalRequestLimit.class);
        if (globalRequestLimit != null) {

            Class<?> declaringClass = handlerMethod.getMethod().getDeclaringClass(); // 定义这个方法的类对象

            // 存在全局限流注解，获取这个注解的属性值
            int limit = globalRequestLimit.requestLimit(); // 限制次数
            int seconds = globalRequestLimit.refreshSeconds(); // 刷新限制的时间
            // 注解参数判断
            if (limit <= 0 || seconds <= 0) {
                // 注解参数有误
                logger.error(
                        "位于:" + declaringClass.getPackage().getName() + "." + declaringClass.getSimpleName() + "-" + handlerMethod.getMethod().getName() +
                        "的@GlobalRequestLimit参数有误,参数都应该为大于等于0的整数。\n" +
                                "请求已放行，请重新配置限流。"
                );
                return true;
            }

            // 开启限流，根据包名 + 类名 + 方法名来唯一定位一个接口
            String redisKey = "limit:" + declaringClass.getPackage().getName() + "." + declaringClass.getSimpleName() + "-" + handlerMethod.getMethod().getName();
            if (!redisService.hasKey(redisKey)) {
                // 没有这个redis键，则初始化一个，设置访问次数为0次，并设置过期时间为刷新限制的时间
                redisService.setCacheObject(redisKey, 0, (long)seconds, TimeUnit.SECONDS);
            }
            int times = redisService.getCacheObject(redisKey); // 获取接口在刷新时间段内已经被访问的次数
            if (times >= limit) {
                // 偶买噶，已经超过了限制次数，拦住拦住！
                logger.warn("位于:" + declaringClass.getPackage().getName() + "." + declaringClass.getSimpleName() + "-" + handlerMethod.getMethod().getName() +
                        "()的接口超过了全局限流器设置的每" + seconds + "秒" + limit + "次的请求限制!已拦截!"
                );
                return requestRejection(response);
            }
            // 更新访问次数和刷新时间
            long lessTime = redisService.getExpire(redisKey);
            if (lessTime <= 0) {
                // 键已经过期，存在这种情况，在我们进行操作的时候他就刚好过期了
                return true; // 直接放行即可
            }
            redisService.setCacheObject(redisKey, times + 1, lessTime, TimeUnit.SECONDS); // 更新
            return true; // 放行接口
        }

        // 2.单用户限流注解
        UserRequestLimit userRequestLimit = handlerMethod.getMethodAnnotation(UserRequestLimit.class);
        if (userRequestLimit != null) {

            Class<?> declaringClass = handlerMethod.getMethod().getDeclaringClass(); // 定义这个方法的类对象

            // 获取用户请求里的token，校验token，并根据token获取用户的唯一标识: user_id
            String token = request.getHeader("Authorization");
            if (token == null) {
                logger.warn("位于:" + declaringClass.getPackage().getName() + "." + declaringClass.getSimpleName() + "-" + handlerMethod.getMethod().getName() +
                        "的@UserRequestLimit标注了一个单用户请求限流，但是收到了一个不具有token的请求!!已拦截请求!");
                return requestRejection(response);
            }
            Claims claims = null;
            try {
                claims = JwtUtils.parseJWT(token);
            } catch (Exception e) {
                logger.warn("位于:" + declaringClass.getPackage().getName() + "." + declaringClass.getSimpleName() + "-" + handlerMethod.getMethod().getName() +
                        "的@UserRequestLimit标注了一个单用户请求限流，但是收到了一个无法被解析token的请求!!已拦截请求!");
                return requestRejection(response);
            }
            String userId = (String) claims.get(SecurityConstants.DETAILS_USER_ID); // 获取其中的user_id

            // 存在单用户限流注解，获取这个注解的属性值
            int limit = userRequestLimit.requestLimit(); // 限制次数
            int seconds = userRequestLimit.refreshSeconds(); // 刷新限制的时间
            // 注解参数判断
            if (limit <= 0 || seconds <= 0) {
                // 注解参数有误
                logger.error(
                        "位于:" + declaringClass.getPackage().getName() + "." + declaringClass.getName() + "-" + handlerMethod.getMethod().getName() +
                                "的@GlobalRequestLimit参数有误,参数都应该为大于等于0的整数。\n" +
                                "请求已放行，请修改配置。"
                );
                return true;
            }

            // 开启限流，根据包名 + 类名 + 方法名 + 用户id 来唯一定位一个用户的接口访问
            String redisKey = "limit:" + declaringClass.getPackage().getName() + "." + declaringClass.getSimpleName() + "-" + handlerMethod.getMethod().getName() + "-" + userId;
            if (!redisService.hasKey(redisKey)) {
                // 没有这个redis键，则初始化一个，设置访问次数为0次，并设置过期时间为刷新限制的时间
                redisService.setCacheObject(redisKey, 0, (long)seconds, TimeUnit.SECONDS);
            }
            int times = redisService.getCacheObject(redisKey); // 获取接口在刷新时间段内已经被访问的次数
            if (times >= limit) {
                // 偶买噶，已经超过了限制次数，拦住拦住！
                logger.warn("位于:" + declaringClass.getPackage().getName() + "." + declaringClass.getSimpleName() + "-" + handlerMethod.getMethod().getName() +
                        "()的接口触发了一次用户限流器设置的每" + seconds + "秒" + limit + "次的请求限制!已拦截!触发用户: " + userId
                );
                return requestRejection(response);
            }
            // 更新访问次数和刷新时间
            long lessTime = redisService.getExpire(redisKey);
            if (lessTime <= 0) {
                // 键已经过期，存在这种情况，在我们进行操作的时候他就刚好过期了
                return true; // 直接放行即可
            }
            redisService.setCacheObject(redisKey, times + 1, lessTime, TimeUnit.SECONDS); // 更新
            return true; // 放行接口
        }

        // 两种限流注解都没有，那就直接放行咯
        return true;
    }

    /**
     * 拒绝一个请求，并向前端返回限流提示
     * @return false
     * */
    private boolean requestRejection(HttpServletResponse response) {
        ResultBody resultBody = ResultBody.error().code(CodeStateEnum.AUTH_REQUEST_LIMIT.code).message(CodeStateEnum.AUTH_REQUEST_LIMIT.message);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write(JSONObject.toJSONString(resultBody));
        } catch (Exception e) {
            logger.error("向前端写出数据时出现异常,错误发生在接口限流时!");
        }
        return false;
    }


    // 把这个过滤器添加为全局过滤器
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(RequestLimitInterceptor.this)
                        .addPathPatterns("/**");
            }
        };
    }

}
