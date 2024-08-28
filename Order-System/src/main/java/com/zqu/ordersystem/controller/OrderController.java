package com.zqu.ordersystem.controller;


import com.zqu.ordersystem.pojo.Order;
import com.zqu.ordersystem.pojo.Result;
import com.zqu.ordersystem.pojo.User;
import com.zqu.ordersystem.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin
public class OrderController {

    @Autowired
    OrderService orderService;

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
}
