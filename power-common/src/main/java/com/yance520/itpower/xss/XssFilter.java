package com.yance520.itpower.xss;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Author : 杨杨
 * Date : 2017/08/20
 * Description :
 * XSS过滤
 * 拦截防止sql注入、xss注入
 */
public class XssFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
                (HttpServletRequest) req);

        chain.doFilter(xssRequest, resp);
    }
    public void destroy() {
    }

}


