package com.zqu.ordersystem.controller;


import com.zqu.ordersystem.pojo.*;
import com.zqu.ordersystem.service.CartDetailService;
import com.zqu.ordersystem.service.CartService;
import com.zqu.ordersystem.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.zqu.ordersystem.utils.JwtUtils.getAudience;

@RestController
@Slf4j
@CrossOrigin
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    CartService cartService;

    @Autowired
    CartDetailService cartDetailService;

    // 获取所有订单
    @GetMapping("/orders")
    public Result getAllOrders(){
        return new Result(orderService.queryAllOrder(),"查询成功",200);
    }
    // 获取单个订单
    @GetMapping("/order/{id}")
    public Result getOneOrder(@PathVariable Integer id){
        return new Result(orderService.queryOneOrder(id),"查询成功",200);
    }
    //分页获取
    @GetMapping("/orders/current/{current}/size/{size}")
    public Result getAllPage(@PathVariable Integer current, @PathVariable Integer size) {
        return new Result(orderService.queryPage(current, size), "查询成功", 200);
    }

    // 条件分页查询
    @PostMapping("/orders/current/{current}/size/{size}")
    public Result getConditionPage(@PathVariable Integer current, @PathVariable Integer size, @RequestBody Order order) {
        return new Result(orderService.queryConditionPage(order, current, size), "查询成功", 200);
    }
    // 创建订单
    @PostMapping("/orders")
    public Result createOrder(@RequestBody Order order){
        orderService.addOrder(order);
        return new Result(null,"创建成功",200);
    }
    // 更新订单
    @PutMapping("/order/{id}")
    public Result updateOrder(@PathVariable Integer id, @RequestBody Order order){
        order.setId(id);
        Integer upd = orderService.updateOrder(order);
        if(upd == null || upd <= 0){
            return new Result(null, "修改失败",401);
        } else {
            return new Result(null, "修改成功", 200);
        }
    }
    // 删除订单
    @DeleteMapping("/order/{id}")
    public Result deleteOrder(@PathVariable Integer id){
        Integer del = orderService.deleteOrder(id);
        if(del == null || del <= 0){
            return new Result(null, "删除失败",401);
        } else {
            return new Result(null, "删除成功", 200);
        }
    }

    //添加购物车订单
    @PostMapping("/order/addCart")
    public Result addToCart(HttpServletRequest req){
        String token = req.getHeader("Authorization");
        log.debug("token is {}", token);
        Integer userId = Integer.valueOf(getAudience(token));
        List<Carts> cartList = cartService.getCartList(userId);
        List<CartDetail> cartDetailList = cartDetailService.queryCartList(cartList);
        Integer i = orderService.addOrderByCart(userId, cartDetailList);
        if(i == null || i <= 0){
            return new Result(null, "添加失败",401);
        } else {
            //清空购物车
            Integer allCart = cartDetailService.reduceAllCart(userId, cartDetailList);
            if(allCart == null || allCart <= 0){
                return new Result(null, "清空购物车失败",401);
            }
            return new Result(null, "添加成功", 200);
        }
    }
}
