package com.qywk.community;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: qlh
 * @introduction: 社区模块
 * @date: 2024/4/18
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.qywk.community.*", "com.qywk.common.*"})
@MapperScan("com.qywk.community.mapper")
public class QYWKCommunityApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(
                QYWKCommunityApplication.class);
        // 开启Banner打印方式(OFF：关闭，CONSOLE：控制台输出，LOG：日志输出)
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
    }
}
