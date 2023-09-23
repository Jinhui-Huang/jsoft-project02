package com.myhd.service.Impl;

import com.myhd.dao.UserDao;
import com.myhd.pojo.User;
import com.myhd.service.UserService;
import com.myhd.util.MyBatisUtil;
import com.mysql.cj.Session;
import org.apache.ibatis.session.SqlSession;

/**
 * className UserServiceImpl
 * packageName com.myhd.service
 * Description TODO
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/09/23 14:02
 */
public class UserServiceImpl implements UserService {
     private static final  SqlSession session =  MyBatisUtil.openSession(true);
     public static final UserDao userDaoImpl =  session.getMapper(UserDao.class);


    @Override
    public Boolean judgeUserIsExists(String account) {
        return userDaoImpl.countAccount(account) == 1;
    }

    @Override
    public User selectByUserAccountPwd(User user) {
        return userDaoImpl.selectUserByAccountPwd(user);
    }

    @Override
    public Boolean updateUserById(User user) {
        return userDaoImpl.updateUserById(user) == 1;
    }
}
