package com.qywk.chatai.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author qlh
 * @date 2024/03/19 10:46
 * @description
 */
@Data
@Component
@ConfigurationProperties(prefix = "chat-ai")
public class ChatAiProperties {
    String api;
    String auth;
}
