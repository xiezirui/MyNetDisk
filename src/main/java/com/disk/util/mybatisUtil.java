package com.disk.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class mybatisUtil {
    private static SqlSessionFactory factory = null;
    static {
        try {
            String resources = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resources);
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
        我们可以从factory中获得 SqlSession 的实例。
        SqlSession 提供了在数据库执行 SQL 命令所需的所有方法。
        SqlSession 实例来直接执行已映射的 SQL 语句
    */

    public static SqlSession getSqlSession() {
        return factory.openSession();
    }

}
