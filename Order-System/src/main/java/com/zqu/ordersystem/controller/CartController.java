package com.zqu.ordersystem.controller;


import com.zqu.ordersystem.pojo.CartDetail;
import com.zqu.ordersystem.pojo.Carts;
import com.zqu.ordersystem.pojo.Result;
import com.zqu.ordersystem.pojo.User;
import com.zqu.ordersystem.service.CartDetailService;
import com.zqu.ordersystem.service.UserService;
import com.zqu.ordersystem.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.zqu.ordersystem.utils.JwtUtils.getAudience;

@RestController
@CrossOrigin
@Slf4j
public class CartController {

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @Autowired
    CartDetailService cartDetailService;

    //新建购物车
    @PostMapping("/cart/create")
    public Result createCart(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        Integer userId = Integer.valueOf(getAudience(token));
        Carts carts = cartService.findOrCreateCartForUser(userId);
        return new Result(carts,"查询成功");
    }


    //添加菜品到购物车
    @PostMapping("/cart/add/{dishesId}")
    public Result addToCart(@PathVariable Integer dishesId, HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        Integer userId = Integer.valueOf(getAudience(token));
        log.debug("userId is {}!!!!!!!!!!!!!!!!!!!", userId);
        Carts carts = cartService.addToCart(userId, dishesId);
        return new Result(carts,"添加成功");
    }

    //查看购物车
    @GetMapping("/cart/list")
    public Result getCartList(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        Integer userId = Integer.valueOf(getAudience(token));
        List<Carts> cartList = cartService.getCartList(userId);
        List<CartDetail> cartDetailList = cartDetailService.queryCartList(cartList);
        return new Result(cartDetailList,"查询成功");
    }

    //减少购物车菜品
    @DeleteMapping("/cart/reduce/{dishesId}")
    public Result reduceCart(@PathVariable Integer dishesId, HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        Integer userId = Integer.valueOf(getAudience(token));
        Integer reduce = cartDetailService.reduceCart(userId,dishesId);
        return new Result("减少成功");
    }

    //计算购物车总价
    @GetMapping("/cart/total")
    public Result getTotalPrice(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        Integer userId = Integer.valueOf(getAudience(token));
        List<Carts> cartList = cartService.getCartList(userId);
        List<CartDetail> cartDetailList = cartDetailService.queryCartList(cartList);
        Double totalPrice = cartDetailService.getTotalPrice(cartDetailList);
        return new Result(totalPrice,"查询成功");
    }

    //清空购物车
    @DeleteMapping("/cart/clear")
    public Result clearCart(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        Integer userId = Integer.valueOf(getAudience(token));
        cartDetailService.reduceAllCart(userId, cartDetailService.queryCartList(cartService.getCartList(userId)));
        return new Result("清空成功");
    }

}
