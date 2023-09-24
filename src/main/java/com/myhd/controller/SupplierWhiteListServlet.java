package com.myhd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.myhd.pojo.SelectLikeInfo;
import com.myhd.pojo.SupplierBlackList;
import com.myhd.pojo.ThreeTablesQuery;
import com.myhd.service.Impl.SupplierWhiteListServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
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
        /*获取参数*/
        Integer enterpriseId = (Integer) req.getSession().getAttribute("enterpriseId");
        String enterpriseName = req.getParameter("enterpriseName");
        String supplierLevel = req.getParameter("supplierLevel");
        Integer startPage = Integer.valueOf(req.getParameter("startPage"));
        /*创建模糊查询pojo对象*/
        SelectLikeInfo sli = new SelectLikeInfo();
        sli.setId(enterpriseId);
        sli.setEnterpriseName(enterpriseName);
        sli.setSupplierLevel(supplierLevel);
        sli.setStartPage(startPage);
        sli.setPageSize(5);
        /*模糊查询*/
        PageInfo<ThreeTablesQuery> info = impl.selectWhiteInfoByEnterpriseId(sli);
        List<ThreeTablesQuery> list = info.getList();
        /*返回json数据*/
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(list);
        resp.getWriter().println(s);
    }

    /**
     * @description 处理‘添加至黑名单’操作,操作完成再次查询
     * @author JoneElmo
     * @date 2023-09-24 16:43
     * @param req
     * @param resp
     * @return void
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取参数*/
        Integer id = Integer.valueOf(req.getParameter("id"));
        Integer enterpriseId = Integer.valueOf(req.getParameter("enterpriseId"));
        Integer supplierId = Integer.valueOf(req.getParameter("supplierId"));
        String reason = req.getParameter("reason");
        /*获取当前日期*/
        Date date = Date.valueOf(LocalDate.now());
        /*生成黑名单pojo对象*/
        SupplierBlackList sbl = new SupplierBlackList();
        sbl.setId(id);
        sbl.setEnterpriseId(enterpriseId);
        sbl.setSupplierId(supplierId);
        sbl.setReason(reason);
        sbl.setUpdateDate(date);
        /*移除白名单并添加至黑名单*/
        try {
            impl.addBlackFromWhite(sbl);
        } catch (Exception e) {
            log.error(e.getMessage(), "移除白名单或加入黑名单失败");
        }
        /*查询新的数据*/
        doGet(req, resp);
    }
}
