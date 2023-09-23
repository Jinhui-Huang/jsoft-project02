package com.myhd.service;

import com.myhd.pojo.User;

/**
 * className UserService
 * packageName com.myhd.service
 * Description TODO 用户服务层具体相关功能。用户登录时用于账户密码校验；将请求的用户数据发给企业认证信息页面；更新企业认证信息页面发送来的数据
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/09/22 19:46
 */
public interface UserService {
    /**
     * @description: 判断用户id是否在用户表里面。
     * @param account
     * @return: java.lang.Boolean
     * @author CYQH
     * @date: 2023/09/22 19:36
     */
    Boolean judgeUserIsExists(String account);
    /**
     * @description: 判断用户账户密码是否正确，如果正确，将查询的信息返回给企业认证页面，如果错误返回一个空对象null。
     * @param user
     * @return: com.myhd.pojo.User
     * @author CYQH
     * @date: 2023/09/22 19:40
     */
    User selectByUserAccountPwd(User user);

    /**
     * @description: 将企业认证页面传过来的用户认证信息更新到用户表中
     * @param user
     * @return: java.lang.Boolean
     * @author CYQH
     * @date: 2023/09/22 19:43
     */
    Boolean updateUserById(User user);
}
