package com.myhd.service.Impl;

import com.myhd.dao.SupplierBlackListDao;
import com.myhd.dao.SupplierWhiteListDao;
import com.myhd.exception.BusinessException;
import com.myhd.pojo.SelectLikeInfo;
import com.myhd.pojo.SupplierBlackList;
import com.myhd.pojo.ThreeTablesQuery;
import com.myhd.service.SupplierBlackListService;
import com.myhd.util.MyBatisUtil;
import com.myhd.util.code.Code;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
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
@Slf4j
public class SupplierBlackListServiceImpl implements SupplierBlackListService {
    private static final SqlSession session =  MyBatisUtil.openSession(true);
    public SupplierBlackListDao sbd =  session.getMapper(SupplierBlackListDao.class);
    public SupplierWhiteListDao swl =  session.getMapper(SupplierWhiteListDao.class);

    @Override
    public List<ThreeTablesQuery> selectBlackInfoByEnterpriseId(SelectLikeInfo selectLikeInfo) {
        try {
            return sbd.selectBlackInfoByEnterpriseId(selectLikeInfo);
        } catch (RuntimeException e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    public Boolean removeBlack(Integer enterprise_id, Integer supplier_id) {
        try {
            return sbd.deleteBlack(enterprise_id,supplier_id) == 1;
        } catch (RuntimeException e) {
            log.error(e.getMessage(),e);
            return false;
        }
    }

    @Override
    public Boolean addBlack(SupplierBlackList supplierBlackList) {
        try {
            Integer i = swl.deleteWhite(supplierBlackList.getEnterpriseId(), supplierBlackList.getSupplierId());
            if (i == 0){
                throw  new BusinessException(Code.DELETE_ERR,"白名单无改条数据");
            }
            Integer j = sbd.insertBlack(supplierBlackList);
            return i == 1 && j == 1 ;
        } catch (RuntimeException e) {
            log.error(e.getMessage(),e);
            return false;
        }
    }
}
