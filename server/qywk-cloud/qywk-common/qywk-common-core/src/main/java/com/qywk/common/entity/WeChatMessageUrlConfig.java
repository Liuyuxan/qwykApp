package com.qywk.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zc
 * @date 202/2/22
 * @description 微信推送所需要的appid、url等配置
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeChatMessageUrlConfig {
    /**
     * 小程序的appid(几乎长期不变)
     */
    private String appId;
    /**
     * 小程序的appSecret(几乎长期不变)
     */
    private String appSecret;

    /**
     * code2session的url(几乎长期不变),用小程序端传过来的code、上面的appId和appSecret换取openid和sessionToken的
     * 数据库里面存了openid和sessionToken的话也可以不用这个url来获取
     */
    //
    private String code2session_url;
    /**
     * 获取accessToken的url(几乎长期不变),accessToken是请求消息推送url时必带的参数
     */
    private String accessToken_url;
    /**
     * 推送消息的url(几乎长期不变)
     */
    private String sendMessage_url;
}
