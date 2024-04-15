package com.qywk.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Ai的文档类型对象
 */
public class Message {
    private String user = "user";
    private String content;

    public Message(String content) {
        this.content = content;
    }
}