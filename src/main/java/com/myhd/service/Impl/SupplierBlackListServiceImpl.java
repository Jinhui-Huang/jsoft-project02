package com.myhd.service.Impl;

import com.myhd.dao.SupplierBlackListDao;
import com.myhd.dao.SupplierWhiteListDao;
import com.myhd.pojo.SelectLikeInfo;
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
    public SupplierBlackListDao sbd =  session.getMapper(SupplierBlackListDao.class);
    public SupplierWhiteListDao swl =  session.getMapper(SupplierWhiteListDao.class);

    @Override
    public List<ThreeTablesQuery> selectBlackInfoByEnterpriseId(SelectLikeInfo selectLikeInfo) {
        return sbd.selectBlackInfoByEnterpriseId(selectLikeInfo);
    }

    @Override
    public Boolean removeBlack(Integer enterprise_id, Integer supplier_id) {
        return sbd.deleteBlack(enterprise_id,supplier_id) == 1;
    }

    @Override
    public Boolean addBlack(SupplierBlackList supplierBlackList) {
        swl.deleteWhite(supplierBlackList.getEnterpriseId(), supplierBlackList.getSupplierId());
        Integer j = sbd.insertBlack(supplierBlackList);
        return j==1;
    }
}
