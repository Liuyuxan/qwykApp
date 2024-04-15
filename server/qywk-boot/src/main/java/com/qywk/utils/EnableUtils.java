package com.qywk.utils;

import com.qywk.common.constant.EnableConstants;

/**
 * @author QiLinHu
 * @date 2024/03/08 13:39
 * @description 激活判断工具类
 */
public class EnableUtils {

    /**
     * 判断是否激活
     * @param state 状态码
     * @return
     */
    public static boolean isEnable(String state){
        int code = getCode(state);
        return code > 0;
    }

    /**
     * 获取激活码
     * @param state
     * @return
     */
    public static int getCode(String state){
        return Integer.getInteger(state.split(":")[0]);
    }

    /**
     * 获取激活描述信息
     * @param state
     * @return
     */
    public static String getMsg(String state){
        return state.split(":")[1];
    }

    /**
     * 是否被封禁
     * @param state
     * @return
     */
    public static boolean isBLOCKED(String state){
        return state.equals(EnableConstants.BLOCKED);
    }

}
