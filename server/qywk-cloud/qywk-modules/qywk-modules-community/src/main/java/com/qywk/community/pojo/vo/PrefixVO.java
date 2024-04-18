package com.qywk.community.pojo.vo;

import lombok.Data;

/**
 * @author ricetofu
 * @date 2024/4/5
 * @description 头像信息返回数据类
 */
@Data
public class PrefixVO {

    /**
     * 头衔的显示名称
     * */
    private String displayName;

    /**
     * 头衔的描述性信息
     * */
    private String details;

    /**
     * 头衔的前端样式信息
     * */
    private String css;
}
