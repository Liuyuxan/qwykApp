package com.qywk.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Ai的文档类型对象
 */
public class Payload {
    private Message[] messages;

    public Payload(String content){
        this.messages = new Message[1];
        this.messages[0] = new Message(content);
    }

    @Override
    public String toString(){
        String payload =
                "{" +
                    "\"messages\":[" +
                        "{" +
                            "\"role\":\"" + this.messages[0].getUser() + "\"," +
                            "\"content\":\"" + this.messages[0].getContent() + "\"" +
                        "}" +
                    "]" +
                "}";
        return payload;
    }
}



