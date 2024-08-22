package com.zqu.ordersystem.service;

import com.zqu.ordersystem.pojo.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface OrderService {
    //查询所有订单
    List<Order> queryAllOrder();

    //查询一个订单
    Order queryOneOrder(Integer orderId);

    //添加一个订单
    @Transactional
    Integer addOrder(Order order);

    //修改一个订单
    @Transactional
    Integer updateOrder(Order order);

    //删除一个订单
    @Transactional
    Integer deleteOrder(Integer orderId);
}
