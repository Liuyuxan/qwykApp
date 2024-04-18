package com.qywk.chatai.service;

import com.qywk.common.core.entity.ResultBody;

/**
 * @author qlh
 * @date 2024/03/19 10:38
 * @description
 */
public interface ChatAiService {
    ResultBody inquiry(String description);
}
