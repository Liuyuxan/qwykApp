package com.qywk.common.core.utils;

import java.security.MessageDigest;

/**
 * @author zc
 * @date 2024/2/19
 * @description md5加密工具类
 */
public class Md5Utils {
    /**
     * 加密字符串获取sign签名，该加密规则是外部接口文档给的，固定不动
     * @param s
     * @return
     */
    public static String MD5BySign(String s) {
        try {
            byte[] btInput = s.getBytes("UTF-8");
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < md.length; i++) {
                int val = ((int) md[i]) & 0xff;
                if (val < 16){
                    sb.append("0");
                }
                sb.append(Integer.toHexString(val));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
