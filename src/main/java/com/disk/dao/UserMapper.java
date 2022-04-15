package com.disk.dao;

import com.disk.pojo.User;

import java.util.HashMap;

public interface UserMapper {
    int addUser(HashMap map);

    User getUser(HashMap map);
}
