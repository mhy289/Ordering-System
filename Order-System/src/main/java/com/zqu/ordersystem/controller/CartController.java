package com.zqu.ordersystem.controller;


import com.zqu.ordersystem.pojo.Carts;
import com.zqu.ordersystem.pojo.Result;
import com.zqu.ordersystem.pojo.User;
import com.zqu.ordersystem.service.UserService;
import com.zqu.ordersystem.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.zqu.ordersystem.utils.JwtUtils.getAudience;

@RestController
@CrossOrigin
public class CartController {

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;


    //添加菜品到购物车
    @PostMapping("/cart/add/{dishesId}")
    public Result addToCart(@PathVariable Integer dishesId, HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        Integer userId = Integer.valueOf(getAudience(token));
        Carts carts = cartService.addToCart(userId, dishesId);
        return new Result(carts,"添加成功");
    }

    //查看购物车
    @GetMapping("/cart/list")
    public Result getCartList(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        Integer userId = Integer.valueOf(getAudience(token));
        return new Result(cartService.getCartList(userId),"查询成功");
    }

}
