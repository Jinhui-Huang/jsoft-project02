package com.myhd.dao;

import com.myhd.pojo.Enterprise;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * className EnterpriseDao
 * packageName com.myhd.dao
 * Description TODO 根据请求返回数据给信息认证页面；无返回数据则需要接受信息认证页面传过来的数据并更新企业表；返回白名单页面或黑名单页面添加供应商时可选择的企业信息
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/09/22 18:12
 */
@Mapper
public interface EnterpriseDao {
    /**
     * @description: 将信息认证页面的企业认证信息更新到企业表中。
     * @param enterprise
     * @return: java.lang.Integer
     * @author CYQH
     * @date: 2023/09/22 20:06
     */
    Integer insertEnterprise(Enterprise enterprise);

    /**
     * @description: 根据查询到的用户表的企业id查询企业信息并将信息返回给信息认证页面。
     * @param enterpriseId
     * @return: com.myhd.pojo.Enterprise
     * @author CYQH
     * @date: 2023/09/22 20:17
     */
    Enterprise selectByEnterpriseId(Integer enterpriseId);
    /**
     * @description: 查询所有企业信息（企业id、企业名称和信用代码）根据查询到的用户表里的企业id来排除查询到的白名单和黑名单供应商企业以及本企业，将查询出的数据作为可选择企业返回到白名单页面。
     * @param enterpriseId
     * @return: java.util.List<com.myhd.pojo.Enterprise>
     * @author CYQH
     * @date: 2023/09/22 20:30
     */
    List<Enterprise> selectEnterpriseExceptWhiteAndBlack(Integer enterpriseId);
    /**
     * @description: 查询所有企业信息（企业id、企业名称和信用代码）根据查询到的用户表里的企业id来排除查询到的黑名单供应商企业以及本企业，将查询出的数据作为可选择企业返回到黑名单页面。
     * @param enterpriseId
     * @return: java.util.List<com.myhd.pojo.Enterprise>
     * @author CYQH
     * @date: 2023/09/22 20:34
     */
    List<Enterprise> selectEnterpriseExceptBlack(Integer enterpriseId);


}
