package com.myhd.service;

import com.myhd.pojo.SelectLikeInfo;
import com.myhd.pojo.SupplierBlackList;
import com.myhd.pojo.SupplierWhiteList;
import com.myhd.pojo.ThreeTablesQuery;

import java.util.List;

/**
 * className SupplierWhiteListService
 * packageName com.myhd.service
 * Description TODO 白名单服务层具体相关功能，对白名单表进行分页查询，并将数据返回给白名单页面，将添加企业供应商的信息更新白名单表；添加黑名单时移除白名单表数据。
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/09/22 19:53
 */
public interface SupplierWhiteListService {
    /**
     * @description: 据用户表查询到的企业id查询本企业的白名单表信息，再根据白名单表信息中的供应商id查询对应用户表信息和企业表信息，将数据存入到三表联查实体类中，并进行分页展示.
     * @param selectLikeInfo
     * @return: java.util.List<com.myhd.pojo.ThreeTablesQuery>
     * @author CYQH
     * @date: 2023/09/23 8:16
     */
    List<ThreeTablesQuery> selectWhiteInfoByEnterpriseId(SelectLikeInfo selectLikeInfo);
    /**
     * @description: 根据选中的白名单供应商信息并填写添加理由后移除白名单表并添加至黑名单中。
     * @param supplierBlackList
     * @return: java.lang.Boolean
     * @author CYQH
     * @date: 2023/09/23 8:20
     */
    Boolean addBlackFromWhite(SupplierBlackList supplierBlackList);
    /**
     * @description: 根据添加供应商获取的白名单信息更新白名单表
     * @param supplierWhiteList
     * @return: java.lang.Boolean
     * @author CYQH
     * @date: 2023/09/23 8:26
     */
    Boolean addWhite(SupplierWhiteList supplierWhiteList);
}
