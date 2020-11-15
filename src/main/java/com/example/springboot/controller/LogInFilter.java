package com.example.springboot.controller;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Order(1)
@WebFilter(filterName = "LogInFilter", urlPatterns = "*")
public class LogInFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Enter LogInFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String password = LogInController.users.get("admin");
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI(); // 这是URI /login
        StringBuffer requestURL = ((HttpServletRequest) servletRequest).getRequestURL(); // 这是对应的URL http://192.168.101.23:8080/login
        if (requestURI.equals("/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if ( password != null && "12345".equals(password)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            PrintWriter writer = httpServletResponse.getWriter();
            writer.print("root"); // 请登录
            writer.close();
        }
        System.out.println("Exit LogInFilter");
    }

    @Override
    public void destroy() {

    }
}
