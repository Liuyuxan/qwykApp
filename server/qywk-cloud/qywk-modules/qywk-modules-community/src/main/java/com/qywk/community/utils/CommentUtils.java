package com.qywk.community.utils;

/**
 * @author qlh
 * @date 2024/04/02 11:09
 * @description 评论工具类
 */
public class CommentUtils {
    /**
     * 判断是否是一级评论，floor楼主，top置顶
     * @param floorId
     * @return
     */
    public static boolean isFloor(String floorId){
        return floorId.equals("floor") || floorId.equals("top");
    }
}
