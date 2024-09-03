package com.zqu.ordersystem.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zqu.ordersystem.maper.DishesMapper;
import com.zqu.ordersystem.maper.OrderDetailMapper;
import com.zqu.ordersystem.maper.OrderMapper;
import com.zqu.ordersystem.maper.UserMapper;
import com.zqu.ordersystem.pojo.*;
import com.zqu.ordersystem.service.DishesService;
import com.zqu.ordersystem.service.OrderDetailService;
import com.zqu.ordersystem.service.OrderService;
import com.zqu.ordersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    DishesMapper dishesMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<OrderDetail> queryAllOrder() {
        return orderDetailMapper.selectAllOrders();
    }

    @Override
    public OrderDetail queryOneOrder(Integer orderId) {
        return orderDetailMapper.selectOrderById(orderId);
    }

    @Override
    public PageItem<OrderDetail> queryPage(Integer current, Integer size) {
        PageHelper.startPage(current, size);
        List<OrderDetail> orderDetailList = orderDetailMapper.selectAllOrders();
        for (OrderDetail orderDetail : orderDetailList) {
            User user = userMapper.getUserById(orderMapper.selectOrderById(orderDetail.getOrderId()).getUserId());
            Order order = orderMapper.selectOrderById(orderDetail.getOrderId());
            order.setUser(user);
            orderDetail.setOrder(order);
            orderDetail.setDishes(dishesMapper.getDishesById(orderDetail.getDishesId()));
        }
        long total = ((Page<OrderDetail>) orderDetailList).getTotal();
        return new PageItem<>(total, orderDetailList);
    }

    @Override
    public PageItem<OrderDetail> queryConditionPage(OrderDetail orderdetail, Integer current, Integer size) {
        PageHelper.startPage(current, size);
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByCondition(orderdetail);
        Page<OrderDetail> info = (Page<OrderDetail>) orderDetailList;
        long total = info.getTotal();
        return new PageItem<>(total, orderDetailList);
    }

    @Override
    public Integer addOrder(OrderDetail orderdetail) {
        return orderDetailMapper.addOrder(orderdetail);
    }

    @Override
    public Integer updateOrder(OrderDetail orderdetail) {
        return orderDetailMapper.updateOrder(orderdetail);
    }

    @Override
    public Integer deleteOrder(Integer orderDetailId) {
        return orderDetailMapper.deleteOrderById(orderDetailId);
    }

    @Override
    public Integer addOrderByOrder(Order order, CartDetail cartDetail) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(order.getId());
        orderDetail.setDishesId(cartDetail.getDishesId());
        orderDetail.setDishesCount(cartDetail.getDishesCount());
        orderDetail.setOrder(order);
        orderDetail.setDishes(cartDetail.getDishes());
        return orderDetailMapper.addOrder(orderDetail);
    }
}
