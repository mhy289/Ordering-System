package com.zqu.ordersystem.controller;

import com.zqu.ordersystem.pojo.CartDetail;
import com.zqu.ordersystem.pojo.Carts;
import com.zqu.ordersystem.pojo.Dishes;
import com.zqu.ordersystem.pojo.Result;
import com.zqu.ordersystem.service.CartDetailService;
import com.zqu.ordersystem.service.DishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class CartDetailController {

    @Autowired
    DishesService dishesService;

    @Autowired
    CartDetailService cartDetailService;

    // 购物车明细表
    @PostMapping("/cart/detail")
    public Result getCartDetail(@RequestBody List<Carts> cartsList) {
        Map<Dishes,Integer> dishesMap = new HashMap<>();
        for (Carts cart : cartsList) {
            List<CartDetail> cartDetails = cartDetailService.queryBycartId(cart.getId());
            for (CartDetail cartDetail : cartDetails) {
                Dishes dishes = dishesService.queryDishById(cartDetail.getDishesId());
                dishesMap.put(dishes,cartDetail.getDishesCount());
            }
        }
        return new Result(dishesMap,"查询成功");
    }
}
