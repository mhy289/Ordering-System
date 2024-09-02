package com.zqu.ordersystem.controller;

import com.zqu.ordersystem.pojo.Result;
import com.zqu.ordersystem.pojo.User;
import com.zqu.ordersystem.service.UserService;
import com.zqu.ordersystem.utils.JwtUtils;
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
        return new Result(userService.queryAllUser(), "懂得的懂", 200);
    }

    // 获取单个用户
    @GetMapping("/user/{id}")
    public Result getUserById(@PathVariable Integer id) {
        return new Result(userService.queryUserById(id), "哈哈哈", 200);
    }

    //分页获取
    @GetMapping("/users/current/{current}/size/{size}")
    public Result getAllPage(@PathVariable Integer current, @PathVariable Integer size) {
        return new Result(userService.queryPage(current, size), "查询成功", 200);
    }

    // 条件分页查询
    @PostMapping("/users/current/{current}/size/{size}")
    public Result getConditionPage(@PathVariable Integer current, @PathVariable Integer size, @RequestBody User user) {
        return new Result(userService.queryConditionPage(user, current, size), "查询成功", 200);
    }

    // 删除单个用户
    @DeleteMapping("/user/{id}")
    public Result deleteUser(@PathVariable Integer id) {
        Integer del = userService.deleteUserById(id);
        if (del == 0) {
            return new Result(null, "删除失败", 500);
        } else {
            return new Result(null, "删除成功", 200);
        }
    }

    // 新增用户
    @PostMapping("/user")
    public Result addUser(@RequestBody User user) {
        Integer add = userService.addUser(user);
        System.out.println(user);
        if (user.getUsername() == null) {
            throw new RuntimeException("未赋值");
        }
        if (add == 0) {
            return new Result(null, "添加失败", 500);
        } else {
            return new Result(null, "添加成功", 200);
        }
    }

    // 修改用户
    @PutMapping("/user/{id}")
    public Result updateUser(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        Integer update = userService.updateUser(user);
        if (update == 0) {
            return new Result(null, "修改失败", 500);
        } else {
            return new Result(null, "修改成功", 200);
        }
    }

    //用户登录
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.debug("user is {}", user);
        Result result = new Result();
        User addDb = userService.login(user);
        if (addDb == null) {
            result.setCode(500);
            result.setMsg("登陆失败");
            return result;
        }
        String token = JwtUtils.createToken(addDb.getId().toString(), addDb.getUsername());
        result.setData(token);
        result.setCode(200);
        result.setMsg("登陆成功");
        return result;
    }

    //用户注册
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        Result result = new Result();
        User addDb = userService.login(user);
        if (addDb!= null) {
            result.setCode(500);
            result.setMsg("该用户名已存在");
            return result;
        }
        Integer add = userService.registerUser(user);
        if (add == 0) {
            result.setCode(500);
            result.setMsg("注册失败");
            return result;
        }
//        String token = JwtUtils.createToken(addDb.getId().toString(), addDb.getUsername());
//        result.setData(token);
        result.setCode(200);
        result.setMsg("注册成功");
        return result;
    }
}
