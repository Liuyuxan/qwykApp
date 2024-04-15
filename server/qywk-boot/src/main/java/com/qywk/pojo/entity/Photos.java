package com.qywk.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * @author QiLinHu
 * @date 2024/03/04 14:02
 * @description 图片集
 */
public class Photos {
    private String key;
    private String img;
    private String enable;
}
