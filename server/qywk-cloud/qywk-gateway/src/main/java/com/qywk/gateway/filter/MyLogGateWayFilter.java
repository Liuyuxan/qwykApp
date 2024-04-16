package com.qywk.gateway.filter;//package com.zjyt.wxzx.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.util.Date;
//
///**
// * @author: qinlj
// * @introduction:
// * @date: 2022/5/31
// */
//@Component
//@Slf4j
//public class MyLogGateWayFilter implements GlobalFilter, Ordered {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        log.info("*************[GlobalLogRecord],DATE:{}", new Date());
//
//        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
////        if (uname == null) {
////            log.error("用户名不存在：{}", uname);
////            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
////            return exchange.getResponse().setComplete();
////        }
//        return chain.filter(exchange);
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//}
