package com.myhd.controller;


import com.myhd.pojo.User;
import com.myhd.util.ReqRespMsgUtil;
import com.myhd.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * className LoginServlet
 * packageName com.myhd.controller
 * Description
 *
 * @author "Jinhui-Huang"
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/09/23 9.04
 */
@Slf4j
@WebServlet("/ls")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入login");
        Cookie[] cookies = req.getCookies();
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        if (cookies != null){
            log.info("cookies存在");
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    if (token != null){
                        log.info("token存在");
                        Map<String, Object> verify = TokenUtil.verify(token, User.class);
                        Boolean status = (Boolean) verify.get("status");
                        if (status){
                            log.info("token正确");
                            ReqRespMsgUtil.sendMsg(resp,token);
                            return;
                        }
                    }
                }
            }
        }
        log.info("cookies不存在或token不存在");
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        String token = TokenUtil.sign(user);
        log.info("生成token====="+token);
        Cookie cookie = new Cookie("token",token);
        /*cookie等有效时间以秒为单位*/
        cookie.setMaxAge(60*60*24*2);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);
        ReqRespMsgUtil.sendMsg(resp,token);
    }
}
