package com.qywk.user.controller;

import com.qywk.common.core.customenum.CodeStateEnum;
import com.qywk.common.core.entity.ResultBody;
import com.qywk.common.redis.utils.RedisLockUtil;
import com.qywk.user.pojo.ao.LoginAO;
import com.qywk.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

/**
 * @author qlh
 * @date 2024/04/18 14:32
 * @description 登录相关接口
 */

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    private RedisLockUtil redisLockUtil;

    /**
     * 普通登录接口
     * @param ao
     * @return
     */
    @PostMapping("normal")
    public ResultBody normal(@RequestBody @NotNull LoginAO ao){
        // 分布式锁防止重复的登陆请求
        RedisLockUtil.Lock lock = redisLockUtil.tryToGetLock("login_" + ao.getUserId(), 10, TimeUnit.SECONDS);

        if (lock == null) return ResultBody.error().code(CodeStateEnum.AUTH_REQUEST_LIMIT.code).message(CodeStateEnum.AUTH_REQUEST_LIMIT.message);

        ResultBody resultBody = loginService.login(ao);

        lock.delete(); // 释放锁
        return resultBody;
    }


}
