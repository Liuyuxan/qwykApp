package com.qywk.chatai.utils;

import com.alibaba.fastjson2.JSONObject;

import com.qywk.common.core.entity.ChatAiResp;
import com.qywk.common.core.entity.Payload;


import com.qywk.chatai.properties.ChatAiProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author qlh
 * @date 2024/03/20 20:56
 * @description
 */
@Component
public class ChatAiUtils {

    @Autowired
    ChatAiProperties chatAiProperties;

    public String inquiry(String description){
        String API_URL = chatAiProperties.getApi();
        String authorization = chatAiProperties.getAuth();

        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", authorization);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            description = StringToJson(description);

            String payload = new Payload(description).toString();

            OutputStream os = connection.getOutputStream();
            byte[] input = payload.getBytes(StandardCharsets.UTF_8);

            os.write(input, 0, input.length);
            os.flush();
            os.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            ChatAiResp answer = JSONObject.parseObject(response.toString(), ChatAiResp.class);
            answer.setResult(answer.getResult());

            return jsonToString(answer.getResult().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String StringToJson(String value) {
        // 使用正则表达式一次性替换所有需要转义的特殊字符
        return value.replaceAll("[\\\\\"]", "\\\\$0")
                .replaceAll("[\b\f\n\r\t]", "");
    }

    public static String jsonToString(String json) {
        // 替换转义后的特殊字符
        String result = json.replaceAll("\\\\\"", "\"") // 双引号
                .replaceAll("\\\\\\\\", "\\\\") // 反斜杠
                .replaceAll("\\\\n", "\n") // 换行
                .replaceAll("\\\\r", "\r") // 回车
                .replaceAll("\\\\t", "\t"); // 水平制表符
        return result;
    }
}
