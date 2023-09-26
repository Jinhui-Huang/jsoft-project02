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
    /**
     * @description: TODO 点击登录按钮进行登录认证，登录成功后将用户账号密码存入token中进行后续的令牌校验。
     * @param req
     * @param resp
     * @return: void
     * @author CYQH
     * @date: 2023/09/25 11:56
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("进入loginPost");
        /*获取login页面请求传输来的账号和密码*/
        User user = ReqRespMsgUtil.getMsg(req, User.class);
        /*判断用户是否存在*/
        boolean userIsExists = user != null && usi.judgeUserIsExists(user.getAccount());
        if (userIsExists){
            /*用户登录方法*/
            log.info(user.toString());
            user = usi.selectByUserAccountPwd(user);
            if (user != null){
                log.info(user.toString());
                log.info("登录成功");
                String token = TokenUtil.sign(user);
                log.info("生成token====="+token);
                Cookie cookie = new Cookie("token",token);
                /*cookie等有效时间以秒为单位*/
                cookie.setMaxAge(60*60*24*2);
                cookie.setPath("/");
                cookie.setSecure(true);
                cookie.setHttpOnly(true);
                resp.addCookie(cookie);
                ReqRespMsgUtil.sendMsg(resp,new Result(Code.GET_OK,true,"账号登录成功"));
            }else {
                ReqRespMsgUtil.sendMsg(resp,new Result(Code.BUSINESS_ERR,false,"账号登录失败"));
            }
        }else {
            ReqRespMsgUtil.sendMsg(resp,new Result(Code.BUSINESS_ERR,false,"该账户不存在"));
        }
    }
    /**
     * @description: TODO 每次进入Login-page页面时都会发送一个get请求，用以判断token是否存在，如果存在并且校验正确则进行自动登录，如果不存在或过期就提示请登录访问。
     * @param req
     * @param resp
     * @return: void
     * @author CYQH
     * @date: 2023/09/25 11:57
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("进入loginGet");
        Cookie[] cookies = req.getCookies();
        boolean flag = false;
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
                                log.info("账号登录成功");
                                flag = true;
                                ReqRespMsgUtil.sendMsg(resp,new Result(Code.GET_OK,true,"账号登录成功"));
                                break;
                            } else {
                                log.warn("账号登录失败");
                                Cookie newCookie = new Cookie("token", null);
                                newCookie.setPath("/");
                                newCookie.setMaxAge(0);
                                resp.addCookie(newCookie);
                            }
                        }
                    }
                    break;
                }
            }
        }
        if (!flag) {
            ReqRespMsgUtil.sendMsg(resp,new Result(Code.GET_ERR, false,"请登录访问"));
        }
    }
    /**
     * @description: TODO 账号退出登录功能
     * @param req
     * @param resp
     * @return: void
     * @author CYQH
     * @date: 2023/09/25 18:55
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("进入loginDelete");
        Cookie newCookie = new Cookie("token", null);
        newCookie.setPath("/");
        newCookie.setMaxAge(0);
        resp.addCookie(newCookie);
        TokenUtil.SERVER_LOCAL.remove();
        ReqRespMsgUtil.sendMsg(resp,new Result(Code.DELETE_OK,true,"账号退出成功"));
    }
}
