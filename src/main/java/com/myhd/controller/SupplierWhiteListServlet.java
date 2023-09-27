package com.myhd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.myhd.pojo.SelectLikeInfo;
import com.myhd.pojo.SupplierBlackList;
import com.myhd.pojo.SupplierWhiteList;
import com.myhd.pojo.ThreeTablesQuery;
import com.myhd.service.Impl.SupplierWhiteListServiceImpl;
import com.myhd.util.ReqRespMsgUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author JoneElmo
 * @date 2023-09-24 19:11
 * @version 1.0
 * @package com.myhd.controller
 * @class SupplierWhiteListServlet
 */
@Slf4j
@WebServlet("/whiteList")
public class SupplierWhiteListServlet extends   HttpServlet {
    private SupplierWhiteListServiceImpl impl = new SupplierWhiteListServiceImpl();

    /**
     * @description 用于处理数据显示请求
     * @author JoneElmo
     * @date 2023-09-24 16:42
     * @param req
     * @param resp
     * @return void
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*设置回复头*/
        resp.setContentType("application/json");
        /*获取请求数据*/
        Integer id = Integer.valueOf(req.getParameter("id"));
        String enterpriseName = req.getParameter("enterpriseName");
        String supplierLevel = req.getParameter("supplierLevel");
        Integer startPage = Integer.valueOf(req.getParameter("startPage"));
        SelectLikeInfo sli = new SelectLikeInfo();
        log.info("拿到的id是："+id);
        log.info("拿到的name是:" + enterpriseName);
        log.info("拿到的level是："+supplierLevel);
        sli.setId(Integer.valueOf(req.getParameter("id")));
        sli.setEnterpriseName(enterpriseName);
        sli.setSupplierLevel( "0".equals(supplierLevel) ? null : supplierLevel );
        sli.setStartPage(startPage);
        /*模糊查询*/
        PageInfo<ThreeTablesQuery> info = impl.selectWhiteInfoByEnterpriseId(sli);
        /*返回json数据*/
        log.info(String.valueOf(info.getList()));
        log.info(String.valueOf(info.getTotal()));
        ReqRespMsgUtil.sendMsg(resp, info);
    }

    /**
     * @description 处理'添加至黑名单'操作,操作完成再次查询
     * @author JoneElmo
     * @date 2023-09-24 16:43
     * @param req
     * @param resp
     * @return void
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取Json数据*/
        SupplierBlackList sbl = ReqRespMsgUtil.getMsg(req, SupplierBlackList.class);
        /*获取当前日期*/
        Date date = Date.valueOf(LocalDate.now());
        /*设置日期*/
        sbl.setUpdateDate(date);
        /*移除白名单并添加至黑名单*/
        Boolean b = false;
        try {
            b = impl.addBlackFromWhite(sbl);
        } catch (Exception e) {
            log.error(e.getMessage(), "移除白名单或加入黑名单失败");
        }
        ReqRespMsgUtil.sendMsg(resp, b);
    }

    /**
     * @description 处理前端的‘添加供应商’请求
     * @author JoneElmo
     * @date 2023-09-26 10:35
     * @param req
     * @param resp
     * @return void
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        SupplierWhiteList whiteList = ReqRespMsgUtil.getMsg(req, SupplierWhiteList.class);
        Date date = Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        whiteList.setUpdateDate(date);
        Boolean aBoolean = impl.addWhite(whiteList);
        /*返回值*/
        ReqRespMsgUtil.sendMsg(resp, aBoolean);
    }
}
