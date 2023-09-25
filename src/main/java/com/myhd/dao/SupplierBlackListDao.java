package com.myhd.dao;

import com.myhd.pojo.SelectLikeInfo;
import com.myhd.pojo.SupplierBlackList;
import com.myhd.pojo.ThreeTablesQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * className SupplierBlackListDao
 * packageName com.myhd.dao
 * Description TODO 对黑名单表进行分页查询，并将数据返回给黑名单页面，将添加企业供应商的信息更新黑名单表；如果添加企业在白名单中就移除白名单；解除黑名单将选定企业从黑名单表中删除uthor "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/09/22 18:13
 */
@Mapper
public interface SupplierBlackListDao {

    /**
     * @description: 根据模糊查询实体类中根据用户表查询到的企业id查询本企业的黑名单表信息，再根据黑名单表信息中的供应商id查询对应用户表信息和企业表信息，在根据模糊查询条件查询对应数据，将数据存入到三表联查实体类中，并进行模糊查询与分页展示。
     * @param selectLikeInfo
     * @return: com.myhd.pojo.ThreeTablesQuery
     * @author CYQH
     * @date: 2023/09/22 20:59
     */
    @Select("<script>\n" +
            "    select blv.* from (select @id:=#{id} p) parm, black_list_vive blv\n" +
            "    <where>\n" +
            "        <if test=\"enterpriseName != null\">\n" +
            "            and enterprise_name like #{enterpriseName}\n" +
            "        </if>\n" +
            "    </where>\n" +
            "</script>")
    List<ThreeTablesQuery> selectBlackInfoByEnterpriseId(SelectLikeInfo selectLikeInfo);

    /**
     * @description: 根据添加供应商获取的黑名单信息更新黑名单表。
     * @param supplierBlackList
     * @return: java.lang.Integer
     * @author CYQH
     * @date: 2023/09/22 21:04
     */
    @Insert("insert into supplier_black_list(enterprise_id, supplier_id, reason, update_date) \n" +
            "values (#{enterpriseId}, #{supplierId} , #{reason} , #{updateDate})")
    Integer insertBlack(SupplierBlackList supplierBlackList);

    /**
     * @description: 根据本企业id和选择的供应商id确定唯一一条黑名单数据并移除黑名单表
     * @param enterpriseId
     * @param supplierId
     * @return: java.lang.Integer
     * @author CYQH
     * @date: 2023/09/22 21:13
     */
    @Delete("delete from supplier_black_list " +
            "where enterprise_id = #{enterpriseId} and supplier_id = #{supplierId}")
    Integer deleteBlack(@Param("enterpriseId") Integer enterpriseId,@Param("supplierId") Integer supplierId);

}
