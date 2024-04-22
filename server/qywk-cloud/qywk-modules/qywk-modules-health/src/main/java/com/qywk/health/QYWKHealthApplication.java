package com.qywk.health;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;

/**
 * @author qlh
 * @date 2024/04/18 0:15
 * @description
 */
public class QYWKHealthApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(
                QYWKHealthApplication.class);
        // 开启Banner打印方式(OFF：关闭，CONSOLE：控制台输出，LOG：日志输出)
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
    }
}
