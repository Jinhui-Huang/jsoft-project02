package com.myhd.service.Impl;

import com.myhd.dao.UserDao;
import com.myhd.pojo.SupplierBlackList;
import com.myhd.pojo.ThreeTablesQuery;
import com.myhd.service.SupplierBlackListService;
import com.myhd.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * className SupplierBlackListServiceImpl
 * packageName com.myhd.service.Impl
 * Description SupplierBlackListServiceImpl
 *
 * @author DY252
 * @version 1.0
 * @email 2385503948@qq.com
 * @Date: 2023/9/24 09:00
 */
public class SupplierBlackListServiceImpl implements SupplierBlackListService {
    private static final SqlSession session =  MyBatisUtil.openSession(true);
    public static final SupplierBlackListService supplierBlackListService =  session.getMapper(SupplierBlackListService.class);
    @Override
    public List<ThreeTablesQuery> selectBlackInfoByEnterpriseId(Integer enterpriseId) {
        return supplierBlackListService.selectBlackInfoByEnterpriseId(enterpriseId);
    }

    @Override
    public Integer removeBlack(Integer enterprise_id, Integer supplier_id) {
        return null;
    }

    @Override
    public Integer addBlack(SupplierBlackList supplierBlackList) {
        return null;
    }
}
