package com.qywk.user.controller;

import com.qywk.common.core.customenum.CodeStateEnum;
import com.qywk.common.core.entity.ResultBody;
import com.qywk.common.redis.utils.RedisLockUtil;
import com.qywk.user.pojo.ao.*;
import com.qywk.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
     * 普通登录
     * @param ao
     * @return
     */
    @PostMapping("normal")
    public ResultBody normal(@RequestBody @Validated LoginAO ao){
        // 分布式锁防止重复的登陆请求
        RedisLockUtil.Lock lock = redisLockUtil.tryToGetLock("login_" + ao.getUserId(), 10, TimeUnit.SECONDS);

        if (lock == null) return ResultBody.error().code(CodeStateEnum.AUTH_REQUEST_LIMIT.code).message(CodeStateEnum.AUTH_REQUEST_LIMIT.message);

        ResultBody resultBody = loginService.login(ao);

        lock.delete(); // 释放锁
        return resultBody;
    }

    /**
     * 微信快速登录
     * @param ao
     * @return
     */
    @PostMapping("fast")
    ResultBody fast(@RequestBody @Validated FastLoginAO ao){
        // 分布式锁防止重复的登陆请求
        RedisLockUtil.Lock lock = redisLockUtil.tryToGetLock("fast_" + ao.getCode(), 10, TimeUnit.SECONDS);

        if (lock == null) return ResultBody.error().code(CodeStateEnum.AUTH_REQUEST_LIMIT.code).message(CodeStateEnum.AUTH_REQUEST_LIMIT.message);

        ResultBody resultBody = loginService.fast(ao.getCode());

        lock.delete(); // 释放锁

        return resultBody;
    }

    /**
     * 注册
     * @param ao
     * @return
     */
    @PostMapping("register")
    public ResultBody register(@RequestBody @NotNull RegisterAO ao){
        // 分布式锁防止重复的登陆请求
        RedisLockUtil.Lock lock = redisLockUtil.tryToGetLock("register_" + ao.getTel(), 10, TimeUnit.SECONDS);

        if (lock == null) return ResultBody.error().code(CodeStateEnum.AUTH_REQUEST_LIMIT.code).message(CodeStateEnum.AUTH_REQUEST_LIMIT.message);

        ResultBody resultBody = loginService.register(ao);

        lock.delete(); // 释放锁

        return resultBody;
    }

    /**
     * 忘记密码
     * @param ao
     * @return
     */
    @PostMapping("forget")
    ResultBody forget(ForgetAO ao){
        // 分布式锁防止重复的登陆请求
        RedisLockUtil.Lock lock = redisLockUtil.tryToGetLock("forgotPassword_" + ao.getUserId(), 10, TimeUnit.SECONDS);

        if (lock == null) return ResultBody.error().code(CodeStateEnum.AUTH_REQUEST_LIMIT.code).message(CodeStateEnum.AUTH_REQUEST_LIMIT.message);

        ResultBody resultBody = loginService.forget(ao);

        lock.delete(); // 释放锁
        return resultBody;
    }

    /**
     * 修改密码
     * @param ao
     * @return
     */
    @PostMapping("change")
    ResultBody changePassword(ChangeAO ao){
        // 分布式锁防止重复的登陆请求
        RedisLockUtil.Lock lock = redisLockUtil.tryToGetLock("changePassword_" + ao.getUserId(), 10, TimeUnit.SECONDS);

        if (lock == null) return ResultBody.error().code(CodeStateEnum.AUTH_REQUEST_LIMIT.code).message(CodeStateEnum.AUTH_REQUEST_LIMIT.message);

        ResultBody resultBody = loginService.changePassword(ao);

        lock.delete(); // 释放锁
        return resultBody;
    }


}
