package com.zqu.ordersystem.controller;

import com.zqu.ordersystem.pojo.Result;
import com.zqu.ordersystem.pojo.User;
import com.zqu.ordersystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    // 获取所有用户
    @GetMapping("/users")
    public Result getAllUsers() {
        return new Result(userService.queryAllUser(),"懂得的懂",200);
    }

    // 获取单个用户
    @GetMapping("/user/{id}")
    public Result getUserById(@PathVariable Integer id) {
        return new Result(userService.queryUserById(id),"哈哈哈",200);
    }

    // 删除单个用户
    @DeleteMapping("/user/{id}")
    public Result deleteUser(@PathVariable Integer id) {
        Integer del = userService.deleteUserById(id);
        if (del == 0) {
            return new Result(null,"删除失败",500);
        }  else {
            return new Result(null,"删除成功",200);
        }
    }

    // 新增用户
    @PostMapping("/user")
    public Result addUser(@RequestBody User user) {
        Integer add = userService.addUser(user);
        System.out.println(user);
        if(user.getUsername() == null) {
            throw new RuntimeException("未赋值");
        }
        if (add == 0) {
            return new Result(null,"添加失败",500);
        } else {
            return new Result(null,"添加成功",200);
        }
    }

    // 修改用户
    @PutMapping("/user/{id}")
    public Result updateUser(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        Integer update = userService.updateUser(user);
        if (update == 0) {
            return new Result(null,"修改失败",500);
        } else {
            return new Result(null,"修改成功",200);
        }
    }
}
