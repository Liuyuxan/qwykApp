package com.qywk.chatai.api;

import com.qywk.chatai.service.ChatAiService;
import com.qywk.chatai.utils.ChatAiUtils;
import com.qywk.common.core.entity.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qlh
 * @date 2024/04/22 9:11
 * @description
 */
@RestController
@RequestMapping("/api")
public class ChatAiApi {
    @Autowired
    ChatAiUtils chatAiUtils;

    @PostMapping("/inquiry")
    public String inquiry(@RequestBody String description){
        return chatAiUtils.inquiry(description);
    }

}
