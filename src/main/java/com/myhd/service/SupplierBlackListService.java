package com.myhd.service;

import com.github.pagehelper.PageInfo;
import com.myhd.pojo.SelectLikeInfo;
import com.myhd.pojo.SupplierBlackList;
import com.myhd.pojo.ThreeTablesQuery;

import java.util.List;

/**
 * className SupplierBlackListService
 * packageName com.myhd.service
 * Description TODO 对黑名单表进行分页查询，并将数据返回给黑名单页面，将添加企业供应商的信息更新黑名单表；如果添加企业在白名单中就移除白名单；解除黑名单将选定企业从黑名单表中删除
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/09/22 19:53
 */
public interface SupplierBlackListService {
    /**
     * @description: 根据用户表查询到的企业id查询本企业的黑名单表信息，再根据黑名单表信息中的供应商id查询对应用户表信息和企业表信息，将数据存入到三表联查实体类中，并进行分页展示.
     * @param selectLikeInfo
     * @return: com.github.pagehelper.PageInfo<com.myhd.pojo.ThreeTablesQuery>
     * @author CYQH
     * @date: 2023/09/23 8:29
     */
    PageInfo<ThreeTablesQuery> selectBlackInfoByEnterpriseId(SelectLikeInfo selectLikeInfo);
    /**
     * @description: 根据本企业id和选择的供应商id确定唯一一条黑名单数据并移除黑名单表
     * @param enterpriseId
     * @param supplierId
     * @return: java.lang.Boolean
     * @author CYQH
     * @date: 2023/09/23 8:31
     */
    Boolean removeBlack(Integer enterpriseId,Integer supplierId);
    /**
     * @description: 根据添加供应商获取的黑名单信息,先查询本企业所有白名单企业，判断要要加入黑名单的企业是否位于白名单，如果位于白名单要先移除白名单再更新黑名单表。
     * @param supplierBlackList
     * @return: java.lang.Boolean
     * @author CYQH
     * @date: 2023/09/23 8:36
     */
    Boolean addBlack(SupplierBlackList supplierBlackList);
}
