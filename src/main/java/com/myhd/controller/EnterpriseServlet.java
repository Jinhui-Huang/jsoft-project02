package com.myhd.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    /**
     * @description 个人信息认证
     * @author JoneElmo
     * @date 2023-09-24 19:13
     * @param req
     * @param resp
     * @return void
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doPost(req, resp);
    }

    /**
     * @description 企业信息认证
     * @author JoneElmo
     * @date 2023-09-24 19:13
     * @param req
     * @param resp
     * @return void
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doPut(req, resp);
    }
}
