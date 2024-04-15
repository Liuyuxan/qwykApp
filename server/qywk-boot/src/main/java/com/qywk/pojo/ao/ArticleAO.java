package com.qywk.pojo.ao;

import lombok.Data;

import java.util.List;

/**
 * @author QiLinHu
 * @date 2024/02/29 21:57
 * @description
 */
@Data
public class ArticleAO {
    /**
     * 描述信息
     */
    private String description;

    /**
     * 图片集
     */
    private List<String> photos;

    /**
     * 分区
     */
    private String subarea;
}
