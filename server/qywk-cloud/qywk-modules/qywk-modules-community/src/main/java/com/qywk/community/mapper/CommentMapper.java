package com.qywk.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qywk.community.pojo.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qlh
 * @date 2024/03/26 8:53
 * @description 评论功能
 */
@Mapper
public interface CommentMapper extends BaseMapper<CommentDTO> {
    List<CommentDTO> selectByFloor(String articleId, Integer offset);

    List<CommentDTO> selectByLeaf(String floorId, Integer offset);

    Integer selectByArticleIdCount(String articleId);

    Integer selectByFloorIdCount(String floorId);
}
