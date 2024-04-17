package com.qywk.common.core.entity;

import lombok.Data;

/**
 * @author qlh
 * @date 2024/03/19 10:34
 * @description chatAi的response解析
 */
@Data
public class ChatAiResp {
    Object id;
    Object object;
    Object created;
    Object result;
}
