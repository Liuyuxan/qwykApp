package com.qywk.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author QiLinHu
 * @date 2024/02/29 23:53
 * @description 配置wechat
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatProperties {
    private String appid;
    private String secret;
}
