package com.bt.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Date;

public class CookleDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        Cookie[] cookies = req.getCookies(); //获得Cookie
        if(cookies !=null){
            out.write("First Viss Time");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie=cookies[i];
                if(cookie.getName().equals("lastLogin")){
                long lastLogin=Long.parseLong(cookie.getValue());
                    Date date = new Date(lastLogin);
                    out.write(date.toLocaleString());
                }
            }
        }
        else {
            out.write("这是你第一次访问");
        }
        Cookie cookie=new Cookie("lastLogin",System.currentTimeMillis()+"");
        resp.addCookie(cookie);
        cookie.setMaxAge(24*60*60);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
