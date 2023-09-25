package com.myhd.filter;

import com.myhd.util.ReqRespMsgUtil;
import com.myhd.util.TokenUtil;
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
    public static final ArrayList<String> paths = new ArrayList<>();

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

        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        Cookie[] cookies = request.getCookies();
        String url = "*";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("reqUrl")) {
                    url = cookie.getValue();
                }
            }
        }
        /*允许前后端分离跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", url);
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        String requestURI = request.getRequestURI();
        /*单/进入*/
        if (requestURI.equals("/")) {
            response.sendRedirect("login-page");
            return;
        }
        /*非/进入, /数量要求少于20*/
        if (countChar(requestURI, '/') >= 20) {
            ReqRespMsgUtil.sendMsg(response, new Result(Code.BUSINESS_ERR, false, "路径不允许过多的/"));
            return;
        }

        /*分割路径*/
        String[] split = requestURI.split("/");
        if (split.length > 1 && paths.contains(split[1])) {
            log.info("过滤路径：" + split[1]);
            request.setCharacterEncoding("UTF-8");
            if (requestURI.equals("/login-page")) {
                chain.doFilter(request, response);
            } else {
                verifyToken(request, response, chain);
            }
        } else {
            response.sendRedirect("http://localhost:8080");
        }

    }

    /*进行令牌验证*/
    private void verifyToken(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        try {
            if (paths.contains(request.getRequestURI().substring(1))) {
                chain.doFilter(request, response);
            } else {
                response.sendRedirect("http://localhost:8080");
            }
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }


    private long countChar(String text, char targetChar) {
        return text.chars().filter(ch -> ch == targetChar).count();
    }

    @Override
    public void destroy() {
        log.info("全路径过滤器销毁");
    }

}