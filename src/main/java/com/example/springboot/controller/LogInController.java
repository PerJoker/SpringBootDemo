package com.example.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

// @RestController combines @Controller and @ResponseBody
@RestController
public class LogInController {
    public static Map<String, String> users = new HashMap<String, String>();
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, HttpSession session) throws ServletException, IOException {
        System.out.println("login");
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = servletRequest.getReader();
        char[] buff = new char[1024];
        int len;
        while ((len = reader.read(buff)) != -1) {
            sb.append(buff, 0, len);
        }
        JSONObject jSONObject = (JSONObject) JSONObject.parse(sb.toString());
        String username = (String) jSONObject.get("username");
        String password = (String) jSONObject.get("password");
        if ( !username.equals("admin") || !password.equals("12345")) {
            PrintWriter writer = httpServletResponse.getWriter();
            writer.print("error"); // 请登录
            writer.close();
        }
        users.putIfAbsent(username, password);
        return "login success!";
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.POST, RequestMethod.GET})
    public String logout(HttpServletRequest servletRequest, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        System.out.println("logout");
        users.remove("admin");
        return "logout success!";
    }

    @RequestMapping(value = "/redirect")
    public String content() {
        System.out.println("redirect");
        return "log failed!";
    }

    @RequestMapping("/")
    public String root(HttpServletRequest request, HttpServletResponse servletResponse, HttpSession session) throws ServletException, IOException {
        Cookie cookie = new Cookie("PerJoker", "YiYao");
        servletResponse.addCookie(cookie);
        return "Hello SpringBoot";
    }
}

