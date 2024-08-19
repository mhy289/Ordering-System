package com.zqu.ordersystem.service.impl;

import com.zqu.ordersystem.maper.UserMapper;
import com.zqu.ordersystem.pojo.User;
import com.zqu.ordersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public User queryUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public Integer deleteUserById(Integer id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateUser(user);
    }
}
