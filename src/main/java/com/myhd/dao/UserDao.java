package com.myhd.dao;

import com.myhd.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * className UserDao
 * packageName com.myhd.dao
 * Description TODO 用户登录时用于账户密码校验；将请求的用户数据发给企业认证信息页面；更新企业认证信息页面发送来的数据
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/09/22 18:10
 */
@Mapper
public interface UserDao {
    /**
     * @description: 判断用户帐号account是否在用户表里面。
     * @param account
     * @return: java.lang.Integer
     * @author CYQH
     * @date: 2023/09/22 19:36
     */
    @Select("SELECT COUNT(id) FROM user WHERE account = #{account}")
    Integer countAccount(String account);
    /**
     * @description: 判断用户账户密码是否正确，如果正确，将查询的信息返回给企业认证页面，如果错误返回一个空对象null。
     * @param user
     * @return: com.myhd.pojo.User
     * @author CYQH
     * @date: 2023/09/22 19:40
     */
    @Select("SELECT id,name,enterprise_id,enterprise_name,phone,account,password,idcard_name,idcard_no FROM user WHERE account = #{account} AND password = #{password}")
    User selectUserByAccountPwd(User user);

    /**
     * @description: 将企业认证页面传过来的用户认证信息更新到用户表中
     * @param user
     * @return: java.lang.Integer
     * @author CYQH
     * @date: 2023/09/22 19:43
     */
    @Update("UPDATE user set enterprise_id = #{enterpriseId} ,enterprise_name = #{enterpriseName},idcard_name = #{idcardName} ,idcard_no = #{idcardNo} WHERE id = #{id}")
    Integer updateUserById(User user);
}
