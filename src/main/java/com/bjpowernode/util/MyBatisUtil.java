package com.bjpowernode.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author fangy
 * @date 2022-01-10 20:16
 */
public class MyBatisUtil {

    private static SqlSessionFactory factory = null;

    static {
        String config = "mybatis.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(config);
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static SqlSession getSqlsession(){
        SqlSession session = null;
        if (factory != null){
           session = factory.openSession();
        }
        return session;
    }
}
