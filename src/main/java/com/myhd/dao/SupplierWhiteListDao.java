package com.myhd.dao;

import com.myhd.pojo.SelectLikeInfo;
import com.myhd.pojo.SupplierWhiteList;
import com.myhd.pojo.ThreeTablesQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * className SupplierWhiteListDao
 * packageName com.myhd.dao
 * Description TODO 对白名单表进行分页查询，并将数据返回给白名单页面，将添加企业供应商的信息更新白名单表；添加黑名单时移除白名单表数据。
 *
 * @author "JoneElmo"
 * @version 1.0
 * @email mhangggggg@outlook.com
 * @Date: 2023-9-23 10:50
 */
@Mapper
public interface SupplierWhiteListDao {

    /**
     * @description: 根据模糊查询实体类中根据用户表查询到的企业id查询本企业的白名单表信息，再根据白名单表信息中的供应商id查询对应用户表信息和企业表信息，在根据模糊查询条件查询对应数据，将数据存入到三表联查实体类中，并进行模糊查询与分页展示。
     * @param selectLikeInfo
     * @return: com.myhd.pojo.ThreeTablesQuery
     * @author JoneElmo
     * @date: 2023-9-23 10:50
     */
    @Select("<script>\n" +
            "    select wlv.* from (select @id:=#{id} p) parm, white_list_vive wlv\n" +
            "    <where>\n" +
            "        <if test=\"enterpriseName != null\">\n" +
            "            and enterprise_name like #{enterpriseName}\n" +
            "        </if>\n" +
            "        <if test=\"supplierLevel != null\">\n" +
            "            and variable_info = #{supplierLevel}\n" +
            "        </if>\n" +
            "    </where>\n" +
            "</script>")
    List<ThreeTablesQuery>  selectWhiteInfoByEnterpriseId(SelectLikeInfo selectLikeInfo);

    /**
     * @description: 根据添加供应商获取的白名单信息更新白名单表。
     * @param supplierWhiteList
     * @return: java.lang.Integer
     * @author JoneElmo
     * @date: 2023-9-23 10:50
     */
    @Insert("insert into supplier_white_list(enterprise_id, supplier_id, supplier_level, update_date) \n" +
            "values (#{enterpriseId}, #{supplierId} , #{supplierLevel} , #{updateDate})")
    Integer insertWhite(SupplierWhiteList supplierWhiteList);

    /**
     * @description: 根据本企业id和选择的供应商id确定唯一一条白名单数据并移除白名单表
     * @param enterpriseId
     * @param supplierId
     * @return: java.lang.Integer
     * @author JoneElmo
     * @date: 2023-9-23 10:50
     */
    @Delete("delete from supplier_white_list " +
            "where enterprise_id = #{enterpriseId} and supplier_id = #{supplierId}")
    Integer deleteWhite(@Param("enterpriseId") Integer enterpriseId,@Param("supplierId") Integer supplierId);

}
