package com.myhd.dao;

import com.myhd.pojo.Enterprise;
import com.myhd.pojo.SelectLikeInfo;
import com.myhd.pojo.ThreeTablesQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * className EnterpriseDao
 * packageName com.myhd.dao
 * Description TODO 根据请求返回数据给信息认证页面；无返回数据则需要接受信息认证页面传过来的数据并更新企业表；返回白名单页面或黑名单页面添加供应商时可选择的企业信息
 *
 * @author "JoneElmo"
 * @version 1.0
 * @email mhangggggg@outlook.com
 * @Date: 2023/09/22 18:12
 */
@Mapper
public interface EnterpriseDao {
    /**
     * @description: 将信息认证页面的企业认证信息更新到企业表中, 通过mybatis把新增的主键返回给插入的对象,在方法上再加如下注解: @Options(useGeneratedKeys = true, keyProperty = "实体类字段名", keyColumn = "数据库表字段名")
     * 注意企业名称和统一信用代码利用数据库的唯一约束进行了查重, 服务层必需要进行异常捕捉。
     * @param enterprise
     * @return: java.lang.Integer
     * @author JoneElmo
     * @date: 2023-9-23 10:50
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into enterprise(name, social_uniform_code, email, phone, address, scale, fax) \n" +
            "values ( #{name},#{socialUniformCode},#{email},#{phone},#{address},#{scale},#{fax} );")
    Integer insertEnterprise(Enterprise enterprise);

    /**
     * @description: 根据查询到的用户表的企业id查询企业信息并将信息返回给信息认证页面。
     * @param enterpriseId
     * @return: com.myhd.pojo.Enterprise
     * @author JoneElmo
     * @date: 2023-9-23 10:50
     */
    @Select("select id, name, social_uniform_code, email, phone, address, scale, fax \n" +
            "from enterprise\n" +
            "where id = #{id}")
    Enterprise selectByEnterpriseId(@Param("id") Integer enterpriseId);

    /**
     * @description: 查询所有企业信息（企业id、企业名称和信用代码）根据查询到的用户表里的企业id来排除查询到的白名单和黑名单供应商企业以及本企业，将查询出的数据作为可选择企业返回到白名单页面。
     * @param enterpriseId
     * @return: java.util.List<com.myhd.pojo.Enterprise>
     * @author JoneElmo
     * @date: 2023-9-23 10:50
     */
    @Select("SELECT e.id, e.name, e.social_uniform_code\n" +
            "FROM enterprise AS e\n" +
            "LEFT JOIN supplier_white_list AS swl ON e.id = swl.supplier_id AND swl.enterprise_id = #{enterpriseId}\n" +
            "LEFT JOIN supplier_black_list AS sbl ON e.id = sbl.supplier_id AND sbl.enterprise_id = #{enterpriseId}\n" +
            "WHERE e.id != #{enterpriseId}\n" +
            "  AND swl.supplier_id IS NULL\n" +
            "  AND sbl.supplier_id IS NULL;")
    List<Enterprise> selectEnterpriseExceptWhiteAndBlack(Integer enterpriseId);

    /**
     * @description: 查询所有企业信息（企业id、企业名称和信用代码）根据查询到的用户表里的企业id来排除查询到的黑名单供应商企业以及本企业，将查询出的数据作为可选择企业返回到黑名单页面。
     * @param enterpriseId
     * @return: java.util.List<com.myhd.pojo.Enterprise>
     * @author JoneElmo
     * @date: 2023-9-23 10:50
     */
    @Select("SELECT id, name, social_uniform_code FROM enterprise AS e " +
            "WHERE e.id != #{enterpriseId} " +
            "AND e.id NOT IN " +
            "(SELECT swl.supplier_id FROM supplier_black_list AS swl " +
            "WHERE swl.enterprise_id = #{enterpriseId})")
    List<Enterprise> selectEnterpriseExceptBlack(Integer enterpriseId);

    /**
     * @description: 根据mybatis动态sql分别判断企业名称，信用代码，邮箱是否重复
     * @param enterprise
     * @return: java.lang.Integer
     * @author CYQH
     * @date: 2023/09/26 8:40
     */
    @Select("<script>\n" +
            "    select COUNT(id) FROM enterprise\n" +
            "    <where>\n" +
            "        <if test=\"name != null\">\n" +
            "            or name = #{name}\n" +
            "        </if>\n" +
            "        <if test=\"socialUniformCode != null\">\n" +
            "            or social_uniform_code = #{socialUniformCode}\n" +
            "        </if>\n" +
            "        <if test=\"email != null\">\n" +
            "            or email = #{email}\n" +
            "        </if>\n" +
            "    </where>\n" +
            "</script>")
    Integer countEnterpriseInfo(Enterprise enterprise);


}
