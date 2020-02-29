package com.java.service.Impl;

import com.java.mapper.UserMapper;
import com.java.po.User;
import com.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public User findUserByPwd(String passWord) {
        return null;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}
