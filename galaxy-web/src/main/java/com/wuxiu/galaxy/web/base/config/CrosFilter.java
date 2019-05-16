package com.wuxiu.galaxy.web.base.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CrossFilter
 *
 * @author wuxiu
 * @Date 2019/4/30 16:46
 */
@Component
@Slf4j
public class CrosFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("**init*********跨域过滤器init*************");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//
//
//        //报错：Request header field authorization is not allowed by Access-Control-Allow-Headers in preflight response.
//        //Access-Control-Allow-Headers没有写全
//        String allowHeaders = "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, Authorization, Accept";
//        response.setHeader("Access-Control-Allow-Headers", allowHeaders);
//
//        log.info("**into*********跨域过滤器被使用*************");
//        filterChain.doFilter(servletRequest, servletResponse);
//        log.info("**success*********跨域过滤器通过*************");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String origin = req.getHeader("Origin");
        if (origin == null) {
            origin = req.getHeader("Referer");
        }
        // 允许指定域访问跨域资源
        resp.setHeader("Access-Control-Allow-Origin", origin);
        // 允许客户端携带跨域cookie，此时origin值不能为“*”，只能为指定单一域名
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        if (RequestMethod.OPTIONS.toString().equals(req.getMethod())) {
            String allowMethod = req.getHeader("Access-Control-Request-Method");
            String allowHeaders = req.getHeader("Access-Control-Request-Headers");
            // 浏览器缓存预检请求结果时间,单位:秒
            resp.setHeader("Access-Control-Max-Age", "86400");
            // 允许浏览器在预检请求成功之后发送的实际请求方法名
            resp.setHeader("Access-Control-Allow-Methods", allowMethod);
            // 允许浏览器发送的请求消息头
            resp.setHeader("Access-Control-Allow-Headers", allowHeaders);
            return;
        }

        log.info("**into*********跨域过滤器被使用*************");
        filterChain.doFilter(req, resp);
        log.info("**success*********跨域过滤器通过*************");
    }

    @Override
    public void destroy() {

    }
}
