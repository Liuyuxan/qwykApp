package com.qywk.gateway;

import com.alibaba.fastjson.JSONObject;
import com.qywk.common.config.gateway.HeaderMapRequestWrapper;
import com.qywk.common.config.properties.WhiteListProperties;
import com.qywk.common.constant.CodeStateEnum;
import com.qywk.common.constant.FilterConstants;
import com.qywk.common.entity.ResultBody;
import com.qywk.utils.JwtUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;


/**
 * @author QiLinHu
 * @date 2024/02/29 22:04
 * @description
 */
@Slf4j
@Component
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    @Autowired
    WhiteListProperties whitelist;

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        //设置请求和响应统一编码格式为UTF-8
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");

        // 获取 url
        String url = req.getRequestURL().toString();
        // 遍历白名单路径列表
        for (String path : whitelist.getPathList()) {
            // 将通配符**替换为正则表达式中的.*
            String regex = ".*" + path.replace("/**", ".*");
            // 使用正则表达式匹配URL
            if (Pattern.matches(regex, url) || url.startsWith(path) || url.contains(path)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }


        // 获取请求头中的令牌（token）
        String jwt = req.getHeader(FilterConstants.AUTHORIZATION);

        // 判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if (!StringUtils.hasLength(jwt)) {
            ResultBody error = ResultBody.error().
                    message("权限模块：登录状态已过期，请重新登录").
                    code(CodeStateEnum.AUTH_LOGIN_EXPIRE.code);
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        // 解析 token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) { // jwt 解析失败
            ResultBody error = ResultBody.error().
                    message("权限模块：登录状态已过期，请重新登录").
                    code(CodeStateEnum.AUTH_LOGIN_EXPIRE.code);
            String notLogin = JSONObject.toJSONString(error);
            log.error(notLogin);
            resp.getWriter().write(notLogin);
            return;
        }

        // 添加header
        HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(req);

        String username = JwtUtils.getUsername(jwt);
        String key = JwtUtils.getKey(jwt);

        requestWrapper.addHeader(FilterConstants.KEY, key);
        requestWrapper.addHeader(FilterConstants.USERNAME, username);
        filterChain.doFilter(requestWrapper, servletResponse);
    }
}
