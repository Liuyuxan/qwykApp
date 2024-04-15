package com.qywk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author qlh
 * @date 2024/2/29 0029 21:07
 * @description 轻养问康-项目
 */

@SpringBootApplication
@MapperScan("com.qywk.mapper")
@ComponentScan("com.qywk.*")

public class QYWKApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(
                QYWKApplication.class);
        // 开启Banner打印方式(OFF：关闭，CONSOLE：控制台输出，LOG：日志输出)
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
    }
}
