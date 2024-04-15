package com.qywk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qywk.pojo.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author QiLinHu
 * @date 2024/02/29 21:59
 * @description
 */
@Mapper
public interface CommentMapper extends BaseMapper<CommentDTO> {
}
