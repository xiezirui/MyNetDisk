package com.disk.service;

import com.disk.pojo.User;

import java.util.HashMap;

public interface UserService {
    HashMap<Object, Object> addUser(String username, String password, String email);

    User getUser(String email, String password);

    int getTotalUserNumber();
}
