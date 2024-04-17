package com.qywk.filter;

import com.qywk.common.core.constant.CacheConstants;
import com.qywk.common.core.constant.SecurityConstants;
import com.qywk.common.core.constant.TokenConstants;
import com.qywk.common.core.customenum.CodeStateEnum;
import com.qywk.common.redis.service.RedisService;
import com.qywk.common.core.utils.JwtUtils;
import com.qywk.common.core.utils.ServletUtils;
import com.qywk.common.core.utils.StringUtils;
import com.qywk.config.properties.IgnoreWhiteProperties;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;


/**
 * @author: qinlj
 * @introduction: 网关鉴权
 * @date: 2022/10/14
 */
@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    // 排除过滤的 uri 地址，nacos自行添加
    @Autowired
    private IgnoreWhiteProperties ignoreWhite;

    @Autowired
    private RedisService redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();

        String url = request.getURI().getPath();
        // 跳过不需要验证的路径
        System.out.println(ignoreWhite.getWhites());
        if (StringUtils.matches(url, ignoreWhite.getWhites())) {

            // 但是如果请求仍然携带了token且token正确，我们仍然要为请求添加上header参数，在模块内部可以使用@RequestHeader直接注入用户id等属性
            String token = getToken(request);
            if(!StringUtils.isEmpty(token) && JwtUtils.parseToken(token) != null){
                String user_id = JwtUtils.getUserId(token);
                boolean isLogin = redisService.hasKey("auth:"+user_id) && redisService.hasKey(JwtUtils.getUserKey(token));
                if(isLogin){
                    // 如果是已登录账号，附加上header参数
                    Claims claims = JwtUtils.parseToken(token);
                    String userId = JwtUtils.getUserId(claims);
                    String username = JwtUtils.getUserName(claims);
                    String userKey = JwtUtils.getUserKey(claims);
                    addHeader(mutate, SecurityConstants.USER_KEY, userKey);
                    addHeader(mutate, SecurityConstants.DETAILS_USER_ID, userId);
                    addHeader(mutate, SecurityConstants.DETAILS_USERNAME, username);
                }
            }

            return chain.filter(exchange);
        }
        String token = getToken(request);
        if (StringUtils.isEmpty(token)) {
            return unauthorizedResponse(exchange,
                    CodeStateEnum.AUTH_TOKEN_NOT_NULL.message, CodeStateEnum.AUTH_TOKEN_NOT_NULL.code);
        }
        Claims claims = JwtUtils.parseToken(token);
        if (claims == null) {
            return unauthorizedResponse(exchange,
                    CodeStateEnum.AUTH_FORBIDDEN.message, CodeStateEnum.AUTH_FORBIDDEN.code);
        }
        String userkey = JwtUtils.getUserKey(claims);
        boolean islogin = redisService.hasKey(userkey);
        if (!islogin) {
            return unauthorizedResponse(exchange,
                    CodeStateEnum.AUTH_LOGIN_EXPIRE.message, CodeStateEnum.AUTH_LOGIN_EXPIRE.code);
        }

        // 每次访问重置redis的缓存时间
        redisService.expire(userkey,3, TimeUnit.DAYS);//3天缓存

        String userid = JwtUtils.getUserId(claims);
        String username = JwtUtils.getUserName(claims);
        if (StringUtils.isEmpty(userid) || StringUtils.isEmpty(username)) {
            return unauthorizedResponse(exchange,
                    CodeStateEnum.AUTH_TOKEN_FAIL.message, CodeStateEnum.AUTH_TOKEN_FAIL.code);
        }

        // 先看redis有没有用户权限节点的缓存
        if(!redisService.hasKey("auth:" + userid)){
            // redis 没有权限记录，直接让用户重新登录以刷新权限
            return unauthorizedResponse(exchange,
                    CodeStateEnum.AUTH_FLUSH_FAIL.message,
                    CodeStateEnum.AUTH_FLUSH_FAIL.code);
        }

        // 权限判断
//        PermissionTree permissionTree = redisService.getCacheObject("auth:" + userid);
//        String path = request.getURI().getPath();
//        if (path.startsWith("/")) {
//            path = path.substring(1);
//        }
//        if(path.endsWith("/")){
//            path = path.substring(0,path.length()-1);
//        }
//
//        path = path.replaceAll("//",".");
//        path = path.replaceAll("/",".");
//
//        if (!permissionTree.havaPermissionNode(path)) {
//            // 无权限
//            return unauthorizedResponse(exchange,
//                    CodeStateEnum.AUTH_NO_PERMISSION.message,
//                    CodeStateEnum.AUTH_NO_PERMISSION.code);
//        }


        // 设置用户信息到请求
        addHeader(mutate, SecurityConstants.USER_KEY, userkey);
        addHeader(mutate, SecurityConstants.DETAILS_USER_ID, userid);
        addHeader(mutate, SecurityConstants.DETAILS_USERNAME, username);
        // 内部请求来源参数清除
        removeHeader(mutate, SecurityConstants.FROM_SOURCE);
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }

    private void addHeader(ServerHttpRequest.Builder mutate, String name, Object value) {
        if (value == null) {
            return;
        }
        String valueStr = value.toString();
        String valueEncode = ServletUtils.urlEncode(valueStr);
        mutate.header(name, valueEncode);
    }

    private void removeHeader(ServerHttpRequest.Builder mutate, String name) {
        mutate.headers(httpHeaders -> httpHeaders.remove(name)).build();
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String msg, int code) {
        log.error("错误码：{},错误信息：{},[鉴权异常处理]请求路径:{}", code, msg, exchange.getRequest().getPath());
        return ServletUtils.webFluxResponseWriter(exchange.getResponse(), msg, code);
    }

    /**
     * 获取缓存key
     */
    private String getTokenKey(String token) {
        return CacheConstants.LOGIN_TOKEN_KEY + token;
    }

    /**
     * 获取请求token
     */
    private String getToken(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(TokenConstants.AUTHENTICATION);
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstants.PREFIX)) {
            token = token.replaceFirst(TokenConstants.PREFIX, StringUtils.EMPTY);
        }
        return token;
    }

    @Override
    public int getOrder() {
        return -200;
    }
}
