package com.qywk.user.api;

import com.qywk.user.pojo.dto.UserInfoDTO;
import com.qywk.user.pojo.vo.UserBasicInfoVO;
import com.qywk.user.pojo.vo.UserInfoVO;
import com.qywk.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author qlh
 * @date 2024/04/22 11:11
 * @description
 */
@RestController
@RequestMapping("/api/info")
public class UserInfoApi {

    @Autowired
    UserInfoService userInfoService;

    /**
     * 查询基础信息
     * @param userId
     * @return
     */
    @GetMapping("basic")
    public UserBasicInfoVO queryBasicInfo(@RequestParam(value = "user_id") String userId){
        return userInfoService.queryBasicInfo(userId);
    }

    /**
     * 获取用户详细信息
     * @param userId
     * @return
     */
    @GetMapping("detail")
    public UserInfoVO queryDetailInfo(@RequestHeader(value = "user_id") String userId){
        return userInfoService.queryDetailInfo(userId);
    }

}
