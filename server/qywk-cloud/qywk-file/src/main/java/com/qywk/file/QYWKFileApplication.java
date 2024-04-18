package com.qywk.file;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;

/**
 * @author qlh
 * @date 2024/04/18 12:55
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class QYWKFileApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(
                QYWKFileApplication.class);
        // 开启Banner打印方式(OFF：关闭，CONSOLE：控制台输出，LOG：日志输出)
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
    }

    @Bean
    public MultipartConfigElement getMultipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置文件最大200M
        factory.setMaxFileSize(DataSize.of(200, DataUnit.MEGABYTES));
        factory.setMaxRequestSize(DataSize.of(200, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }
}
