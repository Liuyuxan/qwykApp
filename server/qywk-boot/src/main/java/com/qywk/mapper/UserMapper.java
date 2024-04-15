package com.qywk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qywk.pojo.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author QiLinHu
 * @date 2024/03/01 0:29
 * @description
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDTO> {
}
