package com.zqu.ordersystem.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zqu.ordersystem.maper.OrderDetailMapper;
import com.zqu.ordersystem.pojo.Order;
import com.zqu.ordersystem.pojo.OrderDetail;
import com.zqu.ordersystem.pojo.PageItem;
import com.zqu.ordersystem.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    OrderDetailMapper orderDetailMapper;

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
}
