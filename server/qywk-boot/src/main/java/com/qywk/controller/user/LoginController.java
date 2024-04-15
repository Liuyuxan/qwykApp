package com.qywk.controller.user;

import com.qywk.common.entity.ResultBody;
import com.qywk.pojo.ao.ForgetAO;
import com.qywk.pojo.ao.LoginAO;
import com.qywk.pojo.ao.RegisterAO;
import com.qywk.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author QiLinHu
 * @date 2024/02/29 21:59
 * @description 用户登录接口
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    /**
     * 普通登录接口
     * @param ao
     * @return
     */
    @PostMapping("/normal")
    public ResultBody normal(@RequestBody LoginAO ao){
        ResultBody ret = loginService.normal(ao);
        return ret;
    }

    /**
     * 微信快速登录
     * @param code
     * @return
     */
    @PostMapping("/fast/wechat")
    public ResultBody fast(@RequestParam(value = "code") String code){
        ResultBody ret = loginService.fast(code);
        return ret;
    }

    /**
     * 手机号注册
     * @param ao
     * @return
     */
    @PostMapping("/register")
    public ResultBody register(@RequestBody RegisterAO ao){
        ResultBody ret = loginService.register(ao);
        return ret;
    }

    @PostMapping("/sendVerify")
    public ResultBody sendVerify(@RequestParam(value = "num") String num){
        ResultBody ret = loginService.sendVerify(num);
        return ret;
    }

    @PostMapping("/forget")
    public ResultBody forget(@RequestBody ForgetAO ao){
        ResultBody ret = loginService.forget(ao);
        return ret;
    }

    @PostMapping("/update")
    public ResultBody update(@RequestBody LoginAO ao, @RequestParam(value = "newPassword") String newPassword){
        ResultBody ret = loginService.update(ao, newPassword);
        return ret;
    }

}
