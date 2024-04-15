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
    public ResultBody getBaseInfo(@RequestParam String username){
        return userService.getBaseInfo(username);
    }

    @GetMapping("/getDetailInfo")
    public ResultBody getDetailInfo(@RequestHeader("username") String username){
        return userService.getDetailInfo(username);
    }


}
