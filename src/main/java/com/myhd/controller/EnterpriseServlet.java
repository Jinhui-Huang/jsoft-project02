package com.myhd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myhd.pojo.Enterprise;
import com.myhd.pojo.User;
import com.myhd.service.Impl.EnterpriseServiceImpl;
import com.myhd.service.Impl.UserServiceImpl;
import com.myhd.util.ReqRespMsgUtil;
import com.myhd.util.ReqRespMsgUtil;
import com.myhd.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

/**
 * @description
 * @author JoneElmo && CYQH
 * @date 2023-09-24 19:10
 * @version 1.0
 * @package com.myhd.controller
 * @class EnterpriseServlet
 */
@Slf4j
@WebServlet("/enterprise")
public class EnterpriseServlet extends HttpServlet {
    private EnterpriseServiceImpl enterpriseImpl = new EnterpriseServiceImpl();
    private UserServiceImpl userImpl = new UserServiceImpl();

    /**
     * @description 个人信息认证
     * @author CYQH
     * @date 2023-09-24 19:13
     * @param req
     * @param resp
     * @return void
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("进入enterprisePost");
        User user = ReqRespMsgUtil.getMsg(req, User.class);
        log.info("请求中的数据:"+user);
        User user1 = (User)TokenUtil.SERVER_LOCAL.get();
        log.info(user1.toString());
        /*获取参数*/
        Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    /*解析token*/
                    Map<String, Object> verify = TokenUtil.verify(token, User.class);
                    user.setId(((User) verify.get(User.class.getSimpleName())).getId());
                    log.info("完整的数据:"+user);
                }
            }
        Boolean aBoolean = userImpl.updateUserById(user);
        ReqRespMsgUtil.sendMsg(resp,aBoolean);
    }

    /**
     * @description 企业信息认证
     * 添加企业信息到数据库后，回显数据
     * @author JoneElmo
     * @date 2023-09-24 19:13
     * @param req
     * @param resp
     * @return void
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        /*获取前端json数据*/
        Enterprise enterprise = ReqRespMsgUtil.getMsg(req, Enterprise.class);
        /*添加企业信息*/
        try {
            enterpriseImpl.addEnterprise(enterprise);
        } catch (Exception e) {
            log.error(e.getMessage(), "更新企业信息失败");
        }
        /*回显数据*/
        Integer id = enterprise.getId();
        Enterprise returnInfo = enterpriseImpl.selectByEnterpriseId(id);
        /*Json格式输出(回显)*/
        ReqRespMsgUtil.sendMsg(resp, returnInfo);
    }
}
