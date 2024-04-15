package com.qywk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qywk.pojo.dto.ArticleDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author QiLinHu
 * @date 2024/02/29 21:55
 * @description
 */
@Mapper
public interface ArticleMapper extends BaseMapper<ArticleDTO> {
}
