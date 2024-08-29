package com.zqu.ordersystem.controller;

import com.zqu.ordersystem.pojo.*;
import com.zqu.ordersystem.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class OrderDetailController {

    @Autowired
    OrderDetailService orderDetailService;

    // 获取所有订单
    @GetMapping("/orderDetails")
    public Result getAllOrders(){
        return new Result(orderDetailService.queryAllOrder(),"查询成功",200);
    }
    // 获取单个订单
    @GetMapping("/orderDetail/{id}")
    public Result getOneOrder(@PathVariable Integer id){
        return new Result(orderDetailService.queryOneOrder(id),"查询成功",200);
    }
    //分页获取
    @GetMapping("/orderDetails/current/{current}/size/{size}")
    public Result getAllPage(@PathVariable Integer current, @PathVariable Integer size) {
        return new Result(orderDetailService.queryPage(current, size), "查询成功", 200);
    }

    // 条件分页查询
    @PostMapping("/orderDetails/current/{current}/size/{size}")
    public Result getConditionPage(@PathVariable Integer current, @PathVariable Integer size, @RequestBody OrderDetail orderDetail) {
        return new Result(orderDetailService.queryConditionPage(orderDetail, current, size), "查询成功", 200);
    }
    // 创建订单
    @PostMapping("/orderDetails")
    public Result createOrder(@RequestBody OrderDetail orderDetail){
        orderDetailService.addOrder(orderDetail);
        return new Result(null,"创建成功",200);
    }
    // 更新订单
    @PutMapping("/orderDetail/{id}")
    public Result updateOrder(@PathVariable Integer id, @RequestBody OrderDetail orderDetail){
        orderDetail.setId(id);
        Integer upd = orderDetailService.updateOrder(orderDetail);
        if(upd == null || upd <= 0){
            return new Result(null, "修改失败",401);
        } else {
            return new Result(null, "修改成功", 200);
        }
    }
    // 删除订单
    @DeleteMapping("/orderDetail/{id}")
    public Result deleteOrder(@PathVariable Integer id){
        Integer del = orderDetailService.deleteOrder(id);
        if(del == null || del <= 0){
            return new Result(null, "删除失败",401);
        } else {
            return new Result(null, "删除成功", 200);
        }
    }

}
