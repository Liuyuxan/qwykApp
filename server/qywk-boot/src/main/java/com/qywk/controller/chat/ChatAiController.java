package com.qywk.controller.chat;

import com.qywk.common.entity.ResultBody;
import com.qywk.service.ChatAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qlh
 * @date 2024/03/19 10:34
 * @description
 */
@RestController
public class ChatAiController {

    @Autowired
    ChatAiService chatAiService;

    @CrossOrigin(origins = "null")
    @PostMapping("/inquiry")
    public ResultBody inquiry(@RequestBody String description){
        return chatAiService.inquiry(description);
    }
}
