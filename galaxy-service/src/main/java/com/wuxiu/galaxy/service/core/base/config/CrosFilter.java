package com.wuxiu.galaxy.service.core.base.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
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
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");

        //报错：Request header field authorization is not allowed by Access-Control-Allow-Headers in preflight response.
        //Access-Control-Allow-Headers没有写全
        String allowHeaders = "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, Authorization";
        response.setHeader("Access-Control-Allow-Headers", allowHeaders);

        log.info("**into*********跨域过滤器被使用*************");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("**success*********跨域过滤器通过*************");
    }

    @Override
    public void destroy() {

    }
}
