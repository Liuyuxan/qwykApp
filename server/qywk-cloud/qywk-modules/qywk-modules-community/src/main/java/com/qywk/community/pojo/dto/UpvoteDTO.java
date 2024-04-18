package com.qywk.community.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qlh
 * @date 2024/04/09 14:50
 * @description 点赞相关
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("upvote")
public class UpvoteDTO {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField("keyword")
    private String keyword;

    @TableField("type")
    private String type;

    @TableField("set_json")
    private String setJson;

    @TableField("hashcode")
    private Integer hashcode;

    @TableField("like_size")
    private Integer likeSize;

    public UpvoteDTO(String keyword, String type, String setJson, Integer hashcode){
        this.keyword = keyword;
        this.type = type;
        this.setJson = setJson;
        this.hashcode = hashcode;
        this.likeSize = 1;
    }
}
