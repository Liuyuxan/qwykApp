package com.qywk.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author qlh
 * @date 2024/04/18 0:15
 * @description
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.qywk.user.*", "com.qywk.common.*"})
@MapperScan("com.qywk.user.mapper")
public class QYWKUserCenterApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(
                QYWKUserCenterApplication.class);
        // 开启Banner打印方式(OFF：关闭，CONSOLE：控制台输出，LOG：日志输出)
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
    }
}