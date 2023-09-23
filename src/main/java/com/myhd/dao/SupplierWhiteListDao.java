package com.myhd.dao;

import com.myhd.pojo.SupplierWhiteList;
import com.myhd.pojo.ThreeTablesQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * className SupplierWhiteListDao
 * packageName com.myhd.dao
 * Description TODO 对白名单表进行分页查询，并将数据返回给白名单页面，将添加企业供应商的信息更新白名单表；添加黑名单时移除白名单表数据。
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/09/22 18:15
 */
@Mapper
public interface SupplierWhiteListDao {
    /**
     * @description: 根据用户表查询到的企业id查询本企业的白名单表信息，再根据白名单表信息中的供应商id查询对应用户表信息和企业表信息，将数据存入到三表联查实体类中，并进行分页展示.
     * @param enterpriseId
     * @return: com.myhd.pojo.ThreeTablesQuery
     * @author CYQH
     * @date: 2023/09/22 20:59
     */
    @Select("SELECT\n" +
            "swl.supplier_id AS supplierID,\n" +
            "    e.name AS EnterpriseName,\n" +
            "    u.idcard_name AS idcardName,\n" +
            "    u.phone,\n" +
            "    e.email,\n" +
            "    swl.supplier_level AS VariableInfo,\n" +
            "    swl.update_date AS updateDate\n" +
            "FROM\n" +
            "    supplier_white_list AS swl\n" +
            "        INNER JOIN\n" +
            "    enterprise AS e ON swl.supplier_id = e.id\n" +
            "        LEFT JOIN\n" +
            "    user AS u ON e.id = u.enterprise_id\n" +
            "WHERE\n" +
            "        swl.enterprise_id = #")
    List<ThreeTablesQuery>  selectWhiteInfoByEnterpriseId(Integer enterpriseId);
    /**
     * @description: 根据添加供应商获取的白名单信息更新白名单表。
     * @param supplierWhiteList
     * @return: java.lang.Integer
     * @author CYQH
     * @date: 2023/09/22 21:04
     */
    Integer insertWhite(SupplierWhiteList supplierWhiteList);
    /**
     * @description: 根据本企业id和选择的供应商id确定唯一一条白名单数据并移除白名单表
     * @param enterprise_id
     * @param supplier_id
     * @return: java.lang.Integer
     * @author CYQH
     * @date: 2023/09/22 21:13
     */
    Integer deleteWhite(Integer enterprise_id,Integer supplier_id);

}
