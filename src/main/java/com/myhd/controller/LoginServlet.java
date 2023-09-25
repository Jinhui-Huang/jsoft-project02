package com.myhd.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.myhd.pojo.User;
import com.myhd.service.Impl.UserServiceImpl;
import com.myhd.util.ReqRespMsgUtil;
import com.myhd.util.Result;
import com.myhd.util.TokenUtil;
import com.myhd.util.code.Code;
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
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserServiceImpl usi = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("进入loginPost");
        ObjectMapper jackson = new ObjectMapper();
        /*获取login页面请求传输来的账号和密码*/
        User user = jackson.readValue(req.getInputStream(), User.class);
        /*判断用户是否存在*/
        Boolean userIsExists = usi.judgeUserIsExists(user.getAccount());
        if (userIsExists){
            /*用户登录方法*/
            user = usi.selectByUserAccountPwd(user);
            if (user != null){
                log.info(user.toString());
                log.info("登录成功");
//                log.info("cookies不存在或token不存在");
                String token = TokenUtil.sign(user);
                log.info("生成token====="+token);
                Cookie cookie = new Cookie("token",token);
                /*cookie等有效时间以秒为单位*/
                cookie.setMaxAge(60*60*24*2);
                cookie.setPath("/");
                cookie.setSecure(true);
                cookie.setHttpOnly(true);
                resp.addCookie(cookie);
                resp.sendRedirect("info-certification");
            }else {
                ReqRespMsgUtil.sendMsg(resp,new Result(Code.BUSINESS_ERR,false,"账号登录失败"));
            }
        }else {
            ReqRespMsgUtil.sendMsg(resp,new Result(Code.BUSINESS_ERR,false,"该账户不存在"));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("进入loginGet");
        Cookie[] cookies = req.getCookies();
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
                            /*用户名密码校验*/
                            User user = (User) verify.get(User.class.getSimpleName());
                            User datebaseUser = usi.selectByUserAccountPwd(user);
                            if (datebaseUser != null){
                                resp.sendRedirect("info-certification");
                            }else {
                                resp.addCookie(new Cookie("token",""));
                                resp.sendRedirect("http://localhost:8080");
                            }
                        }
                    }
                }
            }
        }
    }
}
