package com.qywk.user.service.impl;

import com.qywk.common.core.constant.EnableConstants;
import com.qywk.user.mapper.UserInfoMapper;
import com.qywk.user.pojo.dto.UserInfoDTO;
import com.qywk.user.pojo.vo.UserBasicInfoVO;
import com.qywk.user.pojo.vo.UserInfoVO;
import com.qywk.user.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qlh
 * @date 2024/04/18 14:35
 * @description
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * 查询基础信息
     * @param userId
     * @return
     */
    @Override
    public UserBasicInfoVO queryBasicInfo(String userId) {
        UserInfoDTO user = userInfoMapper.selectById(userId);
        if(user == null || !user.getEnable().equals(EnableConstants.ACTIVATION)){
            return null;
        }
        UserBasicInfoVO basicInfo = new UserBasicInfoVO();
        BeanUtils.copyProperties(user, basicInfo);
        return basicInfo;
    }

    /**
     * 获取用户详细信息
     * @param userId
     * @return
     */
    @Override
    public UserInfoVO queryDetailInfo(String userId) {
        UserInfoDTO user = userInfoMapper.selectById(userId);
        if(user == null || !user.getEnable().equals(EnableConstants.ACTIVATION)){
            return null;
        }
        UserInfoVO detailInfo = new UserInfoVO();
        BeanUtils.copyProperties(user, detailInfo);
        return detailInfo;
    }
}
