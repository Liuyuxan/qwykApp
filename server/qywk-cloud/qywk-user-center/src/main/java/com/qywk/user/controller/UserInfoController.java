package com.qywk.user.controller;

import com.qywk.common.core.entity.ResultBody;
import com.qywk.user.pojo.vo.UserBasicInfoVO;
import com.qywk.user.pojo.vo.UserInfoVO;
import com.qywk.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author qlh
 * @date 2024/04/18 14:32
 * @description 用户信息相关接口
 */
@RestController
@RequestMapping("info")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    /**
     * 查询基础信息
     * @param userId
     * @return
     */
    @GetMapping("basic")
    public ResultBody queryBasicInfo(@RequestParam(value = "user_id") String userId){
        return ResultBody.ok().data("user_info", userInfoService.queryBasicInfo(userId));
    }

    /**
     * 获取用户详细信息
     * @param userId
     * @return
     */
    @GetMapping("detail")
    public ResultBody queryDetailInfo(@RequestHeader(value = "user_id") String userId){
        return ResultBody.ok().data("user_info", userInfoService.queryDetailInfo(userId));
    }

}
