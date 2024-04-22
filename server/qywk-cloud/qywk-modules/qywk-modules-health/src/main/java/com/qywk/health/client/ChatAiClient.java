package com.qywk.health.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author qlh
 * @date 2024/04/22 9:09
 * @description
 */
@FeignClient("qywk-modules-chat-ai")
public interface ChatAiClient {
    @PostMapping("/api/inquiry")
    public String inquiry(@RequestBody String description);
}
