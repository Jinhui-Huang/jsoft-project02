package com.myhd.filter;

import com.myhd.exception.SystemException;
import com.myhd.pojo.User;
import com.myhd.service.Impl.UserServiceImpl;
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
import java.util.Map;

@Slf4j
@javax.servlet.annotation.WebFilter("*")
public class WebFilter implements Filter {
    /*过滤放行路径*/
    public static final ArrayList<String> paths = new ArrayList<>();
    private UserServiceImpl usi = new UserServiceImpl();

    static {
        paths.add("/");
        paths.add("login-page");
        paths.add("info-certification");
        paths.add("white-list");
        paths.add("black-list");
        paths.add("assets");

        paths.add("login");/*LoginServlet映射的地址*/
        paths.add("whiteList"); /*SupplierWhiteListServlet映射的地址*/
        paths.add("blackList"); /*SupplierWhiteListServlet映射的地址*/
        paths.add("enterprise"); /*EnterpriseServlet映射的地址*/

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
            response.sendError(404);
            return;
        }

        /*分割路径*/
        String[] split = requestURI.split("/");
        if (split.length > 1 && paths.contains(split[1])) {
            log.info("过滤路径：" + split[1]);
            request.setCharacterEncoding("UTF-8");
            if (requestURI.equals("/login-page") || requestURI.equals("/login") || split[1].equals("assets")) {
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
        log.info("令牌验证");
        try {
            if (paths.contains(request.getRequestURI().substring(1))) {
                Cookie[] cookies = request.getCookies();
                if (cookies != null){
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("token")){
                            String token = cookie.getValue();
                            if (token != null){
                                Map<String, Object> verify = TokenUtil.verify(token, User.class);
                                Boolean status = (Boolean) verify.get("status");
                                log.info("验证状态为"+status);
                                if (status){
                                    User user = (User) verify.get(User.class.getSimpleName());
                                    User datebaseUser = usi.selectByUserAccountPwd(user);
                                    if (datebaseUser != null){
                                        log.info("token用户信息为："+user);
                                        TokenUtil.SERVER_LOCAL.set(datebaseUser);
                                        chain.doFilter(request,response);
                                        TokenUtil.SERVER_LOCAL.remove();
                                        return;
                                    }else {
                                        response.addCookie(new Cookie("token",""));
                                        response.sendRedirect("http://localhost:8080");
                                    }

                                }
                            }
                        }
                    }
                }
                response.sendRedirect("http://localhost:8080");
            } else {
                response.sendRedirect("http://localhost:8080");
            }
        } catch (IOException | ServletException | RuntimeException e) {
            throw new SystemException(Code.SYSTEM_ERR, "系统未知异常", e);
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