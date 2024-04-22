package com.qywk.user.service;

import com.qywk.user.pojo.dto.UserInfoDTO;
import com.qywk.user.pojo.vo.UserBasicInfoVO;
import com.qywk.user.pojo.vo.UserInfoVO;

/**
 * @author qlh
 * @date 2024/04/18 14:36
 * @description
 */
public interface UserInfoService {

    /**
     * 查询基础信息
     * @param userId
     * @return
     */
    UserBasicInfoVO queryBasicInfo(String userId);

    /**
     * 获取用户详细信息
     * @param userId
     * @return
     */
    UserInfoVO queryDetailInfo(String userId);
}
