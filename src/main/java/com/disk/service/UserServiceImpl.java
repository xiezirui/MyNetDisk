package com.disk.service;

import com.disk.dao.UserMapper;
import com.disk.pojo.User;
import com.disk.util.GetUserId;
import com.disk.util.mybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;

public class UserServiceImpl implements UserService {

    GetUserId getUserId = new GetUserId();

    @Override
    public HashMap addUser(String username, String password, String email) {
        SqlSession sqlSession = null;
        UserMapper mapper = null;
        int i = 0;
        HashMap<Object, Object> returnMap = null;

        try {

        sqlSession = mybatisUtil.getSqlSession();
        mapper = sqlSession.getMapper(UserMapper.class);

        HashMap<Object, Object> map = new HashMap<Object, Object>();
        map.put("id",getUserId.getUUID());
        map.put("username",username);
        map.put("password",password);
        map.put("email",email);

        i = mapper.addUser(map);

        sqlSession.commit();

        returnMap = new HashMap();
        returnMap.put("flag",i);
        returnMap.put("uid",map.get("id"));

        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        }
        sqlSession.close();

        return returnMap;
    }

    @Override
    public User getUser(String email, String password) {
        SqlSession sqlSession = mybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        HashMap<Object, Object> map = new HashMap<Object, Object>();
        map.put("email",email);
        map.put("password",password);

        User user = mapper.getUser(map);

        sqlSession.close();

        return user;
    }

    @Test
    public void addUser(){
        HashMap map = addUser("xiezirui", "123456", "xzr328@outlook.com");
        if (map.get("flag").equals("0")) {
            System.out.println("fail");
        } else {
            System.out.println("success");
            System.out.println(map.get("uid"));
        }
    }

    @Test
    public void getUser(){
        User user = getUser("xzr328@outlook.com", "123456");

        System.out.println(user);
    }
}

