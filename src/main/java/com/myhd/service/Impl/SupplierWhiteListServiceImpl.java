package com.myhd.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myhd.dao.SupplierBlackListDao;
import com.myhd.dao.SupplierWhiteListDao;
import com.myhd.exception.BusinessException;
import com.myhd.pojo.SelectLikeInfo;
import com.myhd.pojo.SupplierBlackList;
import com.myhd.pojo.SupplierWhiteList;
import com.myhd.pojo.ThreeTablesQuery;
import com.myhd.service.SupplierWhiteListService;
import com.myhd.util.MyBatisUtil;
import com.myhd.util.code.Code;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @description 供应商白名单服务层实现，实现以下功能：
 * 1. 白名单界面的模糊查询功能
 * 2. 白名单界面的移入黑名单功能
 * 3. 白名单界面的添加供应商至白名单功能
 * @author JoneElmo
 * @date 2023-09-24 10:42
 * @version 1.0
 * @package com.myhd.service.Impl
 * @class SupplierWhiteListServiceImpl
 */
@Slf4j
public class SupplierWhiteListServiceImpl implements SupplierWhiteListService {
    public static final  SqlSession session =  MyBatisUtil.singleSession(true);
    private SupplierWhiteListDao wDao  = session.getMapper(SupplierWhiteListDao.class);
    private SupplierBlackListDao bDao = session.getMapper(SupplierBlackListDao.class);

    /**
     * @description 白名单界面的模糊查询+分页查询
     * @author JoneElmo
     * @date 2023-09-24 09:58
     * @param selectLikeInfo 该参数由前端页面返回
     * @return java.util.List<com.myhd.pojo.ThreeTablesQuery>
     */
    @Override
    public PageInfo<ThreeTablesQuery> selectWhiteInfoByEnterpriseId(SelectLikeInfo selectLikeInfo) {
        PageHelper.startPage(selectLikeInfo.getStartPage(), selectLikeInfo.getPageSize());
        List<ThreeTablesQuery> trq = wDao.selectWhiteInfoByEnterpriseId(selectLikeInfo);
        return new PageInfo<>(trq);
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
        try {
            Integer i1 = wDao.deleteWhite(sbl.getEnterpriseId(), sbl.getSupplierId());
            if (i1 == 0){
                throw new BusinessException(Code.DELETE_ERR, "无该条数据");
            }
            Integer i2 = bDao.insertBlack(sbl);
            return i1 == 1 && i2 == 1 ;
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            return false;
        }
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
        return wDao.insertWhite(supplierWhiteList) == 1;
    }

}
