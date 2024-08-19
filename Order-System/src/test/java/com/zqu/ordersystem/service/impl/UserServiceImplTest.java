package com.zqu.ordersystem.service.impl;

import com.zqu.ordersystem.pojo.User;
import com.zqu.ordersystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    void addUser() {
        Integer i = userService.addUser(new User(null, "mhy", "123456", 1, null));
        System.out.println(i);
    }
}