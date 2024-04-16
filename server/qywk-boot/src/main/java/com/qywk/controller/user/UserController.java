package com.qywk.controller.user;

import com.qywk.common.entity.ResultBody;
import com.qywk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author QiLinHu
 * @date 2024/03/04 13:13
 * @description 用户信息
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getBaseInfo")
    public ResultBody getBaseInfo(@RequestParam(value = "user_id") String userId){
        return userService.getBaseInfo(userId);
    }

    @GetMapping("/getDetailInfo")
    public ResultBody getDetailInfo(@RequestHeader("user_id") String userId){
        return userService.getDetailInfo(userId);
    }


}
