package com.qywk.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qywk.user.pojo.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author qlh
 * @date 2024/04/18 14:35
 * @description
 */

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoDTO> {
}
