package com.java.service;

import com.java.po.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    void saveUser(User user);

    User findUserByName(String username);

    User findUserByPwd(String passWord);

    void updateUser(User user);
}
