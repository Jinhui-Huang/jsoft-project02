package com.myhd.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;


public class MyBatisUtil {
    //利用static(静态)属于类不属于对象,且全局唯一
    private static final SqlSessionFactory sqlSessionFactory;

    private static SqlSession sqlSession = null;

    //利用静态块在初始化类时实例化sqlSessionFactory
    static {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * openSession 创建一个新的SqlSession对象
     * @return SqlSession对象
     */
    public static SqlSession openSession(boolean autoCommit){
        return sqlSessionFactory.openSession(autoCommit);
    }

    public static SqlSession singleSession(boolean autoCommit) {
        if (sqlSession == null) {
            synchronized (MyBatisUtil.class) {
                sqlSession = openSession(autoCommit);
            }
        }
        return sqlSession;
    }

    public static SqlSession openSession(){
        return sqlSessionFactory.openSession();
    }

    /**
     * 释放一个有效的SqlSession对象
     * @param session 准备释放SqlSession对象
     */
    public static void closeSession(SqlSession session){
        if(session != null){
            session.close();
        }
    }
}

