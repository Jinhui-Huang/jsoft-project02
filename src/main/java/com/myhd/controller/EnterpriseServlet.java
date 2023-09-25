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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("进入enterPriseGet");
        User tokenUser = (User)TokenUtil.SERVER_LOCAL.get();
        Object[] objects = new Object[2];
        objects[0]=tokenUser;
        Enterprise returnInfo = enterpriseImpl.selectByEnterpriseId(tokenUser.getEnterpriseId());
        objects[1]=returnInfo;
        log.info(Arrays.toString(objects));
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
        log.info("请求中的数据:"+user);
        /*从token中获取当前用户id*/
        User tokenUser = (User)TokenUtil.SERVER_LOCAL.get();
        log.info("TokenUtil.SERVER_LOCAL里的数据"+tokenUser.toString());
        user.setId(tokenUser.getId());
        if (userImpl.updateUserById(user)){
            log.info("重新生成TokenUtil.SERVER_LOCAL");
            TokenUtil.SERVER_LOCAL.set(user);
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
        try {
            enterpriseImpl.addEnterprise(enterprise);
        } catch (Exception e) {
            log.error(e.getMessage(), "更新企业信息失败");
        }
        /*回显数据*/
        ReqRespMsgUtil.sendMsg(resp,enterprise);
    }
}
