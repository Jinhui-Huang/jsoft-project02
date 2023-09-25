package com.myhd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.myhd.pojo.SelectLikeInfo;
import com.myhd.pojo.SupplierBlackList;
import com.myhd.pojo.ThreeTablesQuery;
import com.myhd.service.Impl.SupplierBlackListServiceImpl;
import com.myhd.util.ReqRespMsgUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        SelectLikeInfo sli = ReqRespMsgUtil.getMsg(req, SelectLikeInfo.class);
        sli.setPageSize(5);
        /*模糊查询*/
        PageInfo<ThreeTablesQuery> info = impl.selectBlackInfoByEnterpriseId(sli);
        List<ThreeTablesQuery> list = info.getList();
        /*返回json数据*/
        ReqRespMsgUtil.sendMsg(resp, list);
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
        /*移除黑名单*/
        try {
            impl.removeBlack(sbl.getEnterpriseId(),sbl.getSupplierId());
        } catch (Exception e) {
            log.error(e.getMessage(), "解除黑名单失败");
        }finally {
            doGet(req, resp);
        }
    }

}


