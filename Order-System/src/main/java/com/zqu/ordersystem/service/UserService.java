package com.zqu.ordersystem.service;

import com.zqu.ordersystem.pojo.PageItem;
import com.zqu.ordersystem.pojo.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
public interface UserService {
    //查询所有用户
    List<User> queryAllUser();

    //查询一个用户
    User queryUserById(Integer id);

    //分页查询
    PageItem<User> queryPage(Integer current, Integer size);

    //条件分页查询
    PageItem<User> queryConditionPage(User user, Integer current, Integer size);

    //查询一个用户权限
    Integer checkAdmin(Integer id);

    //删除一个用户
    @Transactional
    Integer deleteUserById(Integer id);

    //添加一个用户
    @Transactional
    Integer addUser(User user);

    //修改一个用户
    @Transactional
    Integer updateUser(User user);

    //登录
    @Transactional
    User login(User user);
}
