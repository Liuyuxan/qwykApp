package com.qywk.common.constant;

/**
 * @author QiLinHu
 * @date 2024/03/02 9:27
 * @description 激活状态码
 */
public class EnableConstants {
    // 被举报（举报成功则封禁）
    public static final String REPORT = "2:report";
    // 激活
    public static final String ACTIVATION = "1:activation";
    // 未激活
    public static final String NOT_ACTIVATION = "0:not_activation";
    // 审核
    public static final String VERIFY = "-1:verify";
    // 被封禁
    public static final String BLOCKED = "-2:blocked";
    // 删除
    public static final String DELETE = "-3:delete";

}