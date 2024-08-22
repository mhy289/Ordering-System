package com.zqu.ordersystem.service.impl;

import com.zqu.ordersystem.maper.OrderMapper;
import com.zqu.ordersystem.pojo.Order;
import com.zqu.ordersystem.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<Order> queryAllOrder() {
        return orderMapper.selectAllOrders();
    }

    @Override
    public Order queryOneOrder(Integer orderId) {
        return orderMapper.selectOrderById(orderId);
    }

    @Override
    public Integer addOrder(Order order) {
        return orderMapper.addOrder(order);
    }

    @Override
    public Integer updateOrder(Order order) {
        return orderMapper.updateOrder(order);
    }

    @Override
    public Integer deleteOrder(Integer orderId) {
        return orderMapper.deleteOrderById(orderId);
    }
}
