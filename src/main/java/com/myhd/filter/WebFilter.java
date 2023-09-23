package com.myhd.filter;

import com.myhd.util.ReqRespMsgUtil;
import com.myhd.util.code.Code;
import com.myhd.util.Result;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@javax.servlet.annotation.WebFilter("*")
public class WebFilter implements Filter {
    /*过滤路径*/
    public static  final ArrayList<String> paths = new ArrayList<>();

    static {
        paths.add("/");
        paths.add("login-page");
        paths.add("info-certification");
        paths.add("white-list");
        paths.add("black-list");
        paths.add("assets");

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("全路径过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        log.info("路径进来了");
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        request.setCharacterEncoding("UTF-8");

        Cookie[] cookies = request.getCookies();
        String url = "*";
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("reqUrl")) {
                    url = cookie.getValue();
                }
            }
        }
        response.setHeader("Access-Control-Allow-Origin", url);
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        String requestURI = request.getRequestURI();
        log.info(requestURI);
        /*单/进入*/
        if (paths.contains(requestURI)){
            chain.doFilter(request,response);
        }else if (countChar(requestURI,'/')<20){
            String[] split = requestURI.split("/");
            if (split.length > 1 && paths.contains(split[1])){
                System.out.println("过滤路径："+split[1]);
                chain.doFilter(request,response);
            }else {
                verifyToken(request,response,chain);
            }
        }else {
            ReqRespMsgUtil.sendMsg(response,new Result(Code.BUSINESS_ERR,false,"路径不允许过多的/"));
        }
    }
    /*进行令牌验证*/
    private void verifyToken(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {

    }


    private long countChar(String text, char targetChar) {
        return text.chars().filter(ch -> ch == targetChar).count();
    }

    @Override
    public void destroy() {
        log.info("全路径过滤器销毁");
    }

}