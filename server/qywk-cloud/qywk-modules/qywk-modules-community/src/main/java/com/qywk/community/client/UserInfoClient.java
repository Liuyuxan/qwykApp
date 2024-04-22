package com.qywk.community.client;

import com.qywk.community.pojo.vo.UserBasicInfoVO;
import com.qywk.community.pojo.vo.UserInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author qlh
 * @date 2024/04/22 11:07
 * @description
 */
@FeignClient("qywk-user-center")
public interface UserInfoClient {
    @GetMapping("/api/info/basic")
    UserBasicInfoVO queryBasicInfo(@RequestParam(value = "user_id") String userId);

    @GetMapping("/api/info/detail")
    UserInfoVO queryDetailInfo(@RequestHeader(value = "user_id") String userId);
}
