package com.zqu.ordersystem.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zqu.ordersystem.maper.UserMapper;
import com.zqu.ordersystem.pojo.PageItem;
import com.zqu.ordersystem.pojo.User;
import com.zqu.ordersystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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
    public PageItem<User> queryPage(Integer current, Integer size) {
        PageHelper.startPage(current, size);
        List<User> userList = userMapper.getAllUser();
        long total = ((Page<User>) userList).getTotal();
        return new PageItem<>(total, userList);
    }

    @Override
    public PageItem<User> queryConditionPage(User user, Integer current, Integer size) {
        PageHelper.startPage(current, size);
        List<User> userList = userMapper.selectByCondition(user);
        Page<User> info = (Page<User>) userList;
        long total = info.getTotal();
        return new PageItem<>(total, userList);

    }


    @Override
    public Integer checkAdmin(Integer id) {
        User user = userMapper.getUserById(id);
        return user.getAdmin();
        // return 0 or 1;
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

    @Override
    public User login(User user) {
        String username = user.getUsername();
        User user1 = userMapper.getUserByUsername(username);
        if (user1!=null && user1.getPassword().equals(user.getPassword())) {
            log.info("User is {}",username);
            return user1;
        } else {
            return null;
        }
    }
}
