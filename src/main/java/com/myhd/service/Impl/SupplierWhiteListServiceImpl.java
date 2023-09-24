package com.myhd.service.Impl;

import com.myhd.dao.SupplierBlackListDao;
import com.myhd.dao.SupplierWhiteListDao;
import com.myhd.pojo.SelectLikeInfo;
import com.myhd.pojo.SupplierBlackList;
import com.myhd.pojo.SupplierWhiteList;
import com.myhd.pojo.ThreeTablesQuery;
import com.myhd.service.SupplierWhiteListService;
import com.myhd.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SupplierWhiteListServiceImpl implements SupplierWhiteListService {
    SqlSession session = MyBatisUtil.openSession(true);
    SupplierWhiteListDao wDao  = session.getMapper(SupplierWhiteListDao.class);
    SupplierBlackListDao bDao = session.getMapper(SupplierBlackListDao.class);

    /**
     * @description 白名单界面的模糊查询功能
     * @author JoneElmo
     * @date 2023-09-24 09:58
     * @param selectLikeInfo 该参数由前端页面返回
     * @return java.util.List<com.myhd.pojo.ThreeTablesQuery>
     */
    @Override
    public List<ThreeTablesQuery> selectWhiteInfoByEnterpriseId(SelectLikeInfo selectLikeInfo) {

        return wDao.selectWhiteInfoByEnterpriseId(selectLikeInfo);
    }

    /**
     * @description 将记录从白名单移动到黑名单中。分为两步：1. 移除白名单 2. 添加至黑名单 。两步操作都成功时，返回true,否则返回false.
     * @author JoneElmo
     * @date 2023-09-24 09:08
     * @param sbl
     * @return java.lang.Boolean
     */
    @Override
    public Boolean addBlackFromWhite(SupplierBlackList sbl) {
        Integer i1 = wDao.deleteWhite(sbl.getEnterpriseId(), sbl.getSupplierId());
        Integer i2 = bDao.insertBlack(sbl);
        return i1==1&&i2==1?true:false;
    }

    /**
     * @description 将企业添加至白名单，调用此方法
     * @author JoneElmo
     * @date 2023-09-24 09:11
     * @param supplierWhiteList
     * @return java.lang.Boolean
     */
    @Override
    public Boolean addWhite(SupplierWhiteList supplierWhiteList) {
        Integer i = wDao.insertWhite(supplierWhiteList);
        return i==1?true:false;
    }

}
