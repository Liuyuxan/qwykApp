package com.qywk;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: qinlj
 * @introduction:
 * @date: 2022/5/26
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
 public class QYWKGateWayApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(
                QYWKGateWayApplication.class);
        // 开启Banner打印方式(OFF：关闭，CONSOLE：控制台输出，LOG：日志输出)
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
    }
}
