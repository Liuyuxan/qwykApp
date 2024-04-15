package com.qywk.utils;

import cn.hutool.core.convert.Convert;
import com.qywk.common.constant.FilterConstants;
import com.qywk.common.constant.TokenConstants;
import com.qywk.pojo.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author QiLinHu
 * @date 2024/02/29 22:43
 * @description jwt token令牌
 */
public class JwtUtils {

    private static final String signKey = TokenConstants.SIGN_KEY;
    private static final Long expire = TokenConstants.EXPIRE;

    public static String getToken(UserDTO user){
        Map<String, Object> claims = new HashMap<>();
        claims.put(FilterConstants.KEY, user.getId());
        claims.put(FilterConstants.USERNAME, user.getUsername());
        return generateJwt(claims);
    }

    /**
     * 生成 JWT 令牌
     * @param claims
     * @return
     */
    private static String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }

    /**
     * 解析 JWT 令牌
     * @param jwt
     * @return
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }

    /**
     * 根据令牌获取用户标识
     *
     * @param jwt 令牌
     * @return id
     */
    public static String getKey(String jwt) {
        Claims claims = parseJWT(jwt);
        return getValue(claims, FilterConstants.KEY);
    }

    /**
     * 根据令牌获取用户标识
     *
     * @param jwt 令牌
     * @return username
     */
    public static String getUsername(String jwt) {
        Claims claims = parseJWT(jwt);
        return getValue(claims, FilterConstants.USERNAME);
    }

    /**
     * 根据身份信息获取键值
     *
     * @param claims 身份信息
     * @param key    键
     * @return 值
     */
    public static String getValue(Claims claims, String key) {
        return Convert.toStr(claims.get(key), "");
    }

}
