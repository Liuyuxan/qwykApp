package com.qywk.common.entity;

import lombok.Data;

/**
 * @author zc
 * @date 2023/11/27
 * @description 微信消息推送配置模版类,属性不能空值，不然抛异常
 */
@Data
public class WeChatMessageConfig {
    /**
     * 消息接受者的微信openid,要用到
     */
    private String touser;
    /**
     * 订阅的消息模版id,也就是模版template_id
     */
    private String template_id;
    /**
     * 点击消息后跳转前端页面包路径
     */
    private String page;
    /**
     * 跳转小程序类型：developer为开发版；trial为体验版；formal为正式版；默认为正式版
     */
    private String miniprogram_state;
    /**
     * 推送给用户的数据,建议按申请模版的格式传一个map对象
     */
    private Object data;
}
