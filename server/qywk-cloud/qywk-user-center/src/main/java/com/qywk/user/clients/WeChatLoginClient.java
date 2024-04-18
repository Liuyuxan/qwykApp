package com.qywk.user.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: qlh
 * @date: 2024/4/18
 * @introduction: 微信登陆功能远程调用
 * */
@FeignClient(value = "wechatLoginClient", url = "127.0.0.1:433/sns") // url这么写是因为做了nginx反向代理(https://api.weixin.qq.com/sns/jscode2session)
public interface WeChatLoginClient {
    /**
     * 登录凭证校验，返回结果参考及接口文档详见:
     * https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/user-login/code2Session.html
     * */
    @GetMapping("/jscode2session")
    String login(
            @RequestParam("appid") String appid,
            @RequestParam("secret") String secret,
            @RequestParam("js_code") String js_code,
            @RequestParam("grant_type") String grant_type
    );
}
