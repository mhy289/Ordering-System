package com.zqu.ordersystem.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zqu.ordersystem.maper.OrderMapper;
import com.zqu.ordersystem.pojo.Order;
import com.zqu.ordersystem.pojo.PageItem;
import com.zqu.ordersystem.pojo.User;
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
    public PageItem<Order> queryPage(Integer current, Integer size) {
        PageHelper.startPage(current, size);
        List<Order> orderList = orderMapper.selectAllOrders();
        long total = ((Page<Order>) orderList).getTotal();
        return new PageItem<>(total, orderList);
    }

    @Override
    public PageItem<Order> queryConditionPage(Order order, Integer current, Integer size) {
        PageHelper.startPage(current, size);
        List<Order> orderList = orderMapper.selectByCondition(order);
        Page<Order> info = (Page<Order>) orderList;
        long total = info.getTotal();
        return new PageItem<>(total, orderList);
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
