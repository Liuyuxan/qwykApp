package com.qywk.service.impl;

import com.qywk.common.constant.CodeStateEnum;
import com.qywk.common.entity.ResultBody;
import com.qywk.service.ChatAiService;
import com.qywk.utils.ChatAiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qlh
 * @date 2024/03/19 10:38
 * @description
 */
@Service
public class ChatAiServiceImpl implements ChatAiService {

    @Autowired
    ChatAiUtils chatAiUtils;

    @Override
    public ResultBody inquiry(String description) {
        String answer = chatAiUtils.inquiry(description);
        if(answer != null) return ResultBody.ok().data("answer", answer);
        return ResultBody.error().message(CodeStateEnum.ERROR.message).code(CodeStateEnum.ERROR.code);
    }
}
