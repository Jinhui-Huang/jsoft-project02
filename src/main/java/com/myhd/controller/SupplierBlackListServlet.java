package com.myhd.controller;

import com.github.pagehelper.PageInfo;
import com.myhd.pojo.SelectLikeInfo;
import com.myhd.pojo.SupplierBlackList;
import com.myhd.pojo.ThreeTablesQuery;
import com.myhd.service.Impl.SupplierBlackListServiceImpl;
import com.myhd.util.ReqRespMsgUtil;
import com.myhd.util.Result;
import com.myhd.util.code.Code;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * className SupplierBlackListServlet
 * packageName com.myhd.controller
 * Description 用于供应商黑名单页面
 *
 * @author DY252
 * @version 1.0
 * @email 2385503948@qq.com
 * @Date: 2023/9/24 20:29
 */
@Slf4j
@WebServlet("/blackList")
public class SupplierBlackListServlet extends HttpServlet {
    private SupplierBlackListServiceImpl impl = new SupplierBlackListServiceImpl();

    /**
     * @description: 用于处理数据显示请求
     * @param req,resp
     * @return: void
     * @author DY
     * @date: 2023/09/24 20:36
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*设置回复头*/
        resp.setContentType("application/json");
        /*获取Json数据*/
//        SelectLikeInfo sli = ReqRespMsgUtil.getMsg(req, SelectLikeInfo.class);
        Integer id = Integer.valueOf(req.getParameter("id"));
        String enterpriseName = req.getParameter("enterpriseName");
        Integer startPage = Integer.valueOf(req.getParameter("startPage"));
        log.info("拿到的id是："+id);
        log.info("拿到的name是:" + enterpriseName);
        log.info("拿到的startPage是:" + startPage);
        SelectLikeInfo sli = new SelectLikeInfo();
        sli.setId(id);
        sli.setEnterpriseName(enterpriseName);
        sli.setStartPage(startPage);
        /*模糊查询*/
        PageInfo<ThreeTablesQuery> info = impl.selectBlackInfoByEnterpriseId(sli);
        /*返回json数据*/
        ReqRespMsgUtil.sendMsg(resp, info);
    }

    /**
     * @description: 处理‘解除黑名单’操作,操作完成再次查询
     * @param: req,resp
     * @return: void
     * @author DY252
     * @date: 2023/9/24 21:10
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取Json数据*/
        SupplierBlackList sbl = ReqRespMsgUtil.getMsg(req, SupplierBlackList.class);
        log.info("企业id"+sbl.getEnterpriseId().toString());
        log.info("供应商id"+sbl.getSupplierId().toString());
        /*移除黑名单*/
        try {
            Boolean removeBlack = impl.removeBlack(sbl.getEnterpriseId(), sbl.getSupplierId());
            if (removeBlack){
                ReqRespMsgUtil.sendMsg(resp,new Result(Code.DELETE_OK,true,"移除黑名单成功"));
            }else {
                ReqRespMsgUtil.sendMsg(resp,new Result(Code.DELETE_ERR,false,"移除黑名单失败"));
            }
        } catch (Exception e) {
            ReqRespMsgUtil.sendMsg(resp,new Result(Code.DELETE_ERR,false,"移除黑名单失败"));
        }
    }

    /** 
     * @description: 添加供应商到黑名单
     * @param: req,resp
     * @return: void
     * @author DY252
     * @date: 2023/9/25 14:26
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
        try {
            Boolean removeBlack = impl.addBlack(sbl);;
            if (removeBlack){
                ReqRespMsgUtil.sendMsg(resp,new Result(Code.DELETE_OK,true,"添加供应商成功"));
            }else {
                ReqRespMsgUtil.sendMsg(resp,new Result(Code.DELETE_ERR,false,"添加供应商失败"));
            }
        } catch (Exception e) {
            ReqRespMsgUtil.sendMsg(resp,new Result(Code.DELETE_ERR,false,"添加供应商失败"));
        }
    }

}


