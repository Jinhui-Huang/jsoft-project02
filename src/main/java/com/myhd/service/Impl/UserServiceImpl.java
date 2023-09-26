package com.myhd.service.Impl;

import com.myhd.dao.UserDao;
import com.myhd.pojo.User;
import com.myhd.service.UserService;
import com.myhd.util.MyBatisUtil;
import com.mysql.cj.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
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
@Slf4j
public class UserServiceImpl implements UserService {
    public static final  SqlSession session =  MyBatisUtil.singleSession(true);
     private UserDao userDaoImpl =  session.getMapper(UserDao.class);

    @Override
    public Boolean judgeUserIsExists(String account) {
        if (account == null || "".equals(account)){
            return false;
        }
        return userDaoImpl.countAccount(account) == 1;
    }

    @Override
    public User selectByUserAccountPwd(User user) {
        if (user.getPassword() == null){
            return null;
        }
        return userDaoImpl.selectUserByAccountPwd(user);
    }

    @Override
    public Boolean updateUserById(User user) {
        Integer integer;
        try {
            integer = userDaoImpl.updateUserById(user);
        } catch (PersistenceException e) {
            log.warn("数据重复");
            return false;
        }
        return integer == 1;
    }
}
