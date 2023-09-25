package com.myhd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myhd.pojo.Enterprise;
import com.myhd.pojo.User;
import com.myhd.service.Impl.EnterpriseServiceImpl;
import com.myhd.service.Impl.UserServiceImpl;
import com.myhd.util.ReqRespMsgUtil;
import com.myhd.util.ReqRespMsgUtil;
import com.myhd.util.Result;
import com.myhd.util.TokenUtil;
import com.myhd.util.code.Code;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;
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
     * @description: TODO 从数据库获取数据进行认证页面数据的回显,每次信息认证页面加载都会发送get请求，将用户信息和企业信息分别放在objects[0]和objects[1]上，用户第一次登录时，因为没有认证所以无法查询到对应的企业信息，根据objects[1]是否为null判断该用户是否进行了认证，并且是否进行数据回显。
     * @param req
     * @param resp
     * @return: void
     * @author CYQH
     * @date: 2023/09/25 18:55
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        log.info("进入enterPriseGet");
        User tokenUser = (User)TokenUtil.SERVER_LOCAL.get();
        Object[] objects = new Object[2];
        objects[0]=tokenUser;
        Enterprise returnInfo = enterpriseImpl.selectByEnterpriseId(tokenUser.getEnterpriseId());
        objects[1]=returnInfo;
        req.getSession().setAttribute("enterpriseId", tokenUser.getEnterpriseId());
        ReqRespMsgUtil.sendMsg(resp,new Result(Code.UPDATE_OK,objects,"用户信息回显"));
    }

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
        /*将请求信息中的user属性赋值给tokenUser*/
        User tokenUser = (User)TokenUtil.SERVER_LOCAL.get();
        user.setId(tokenUser.getId());
        /*更新用户的信息*/
        if (userImpl.updateUserById(user)){
            ReqRespMsgUtil.sendMsg(resp,new Result(Code.UPDATE_OK,true,"用户信息认证成功"));
        }else {
            ReqRespMsgUtil.sendMsg(resp,new Result(Code.UPDATE_ERR,false,"用户信息认证失败"));
        }

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
        boolean flag = false;
        try {
             if (enterpriseImpl.addEnterprise(enterprise)){
                 flag = true;
             }
        } catch (Exception e) {
            log.error(e.getMessage(), "更新企业信息失败");
        }
        Object[] objects = {flag,enterprise};
        /*回显数据*/
        ReqRespMsgUtil.sendMsg(resp,new Result(Code.UPDATE_ERR,objects,"企业信息认证失败"));
    }
}
