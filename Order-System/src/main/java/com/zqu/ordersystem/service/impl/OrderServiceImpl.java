package com.zqu.ordersystem.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zqu.ordersystem.maper.OrderMapper;
import com.zqu.ordersystem.pojo.CartDetail;
import com.zqu.ordersystem.pojo.Order;
import com.zqu.ordersystem.pojo.PageItem;
import com.zqu.ordersystem.pojo.User;
import com.zqu.ordersystem.service.OrderDetailService;
import com.zqu.ordersystem.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderDetailService orderDetailService;

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

    @Override
    public Integer addOrderByCart(Integer userId, List<CartDetail> cartDetailList) {
        //Order order = orderMapper.selectOrderByuserId(userId);
//        if(order == null){
//            //新建用户订单
//        }
        Order order = new Order();
        order.setUserId(userId);
        order.setStatus(0); // 0 未支付
        order.setOrderTime(new Date());
        Double totalPrice = 0.0;
        orderMapper.addOrder(order);
        //order.setId();
        for(CartDetail cartDetail : cartDetailList){
            totalPrice += (cartDetail.getDishesCount()*cartDetail.getDishes().getPrice());
            orderDetailService.addOrderByOrder(order, cartDetail);
        }
        order.setTotalPrice(totalPrice);
        Integer i = orderMapper.updateOrder(order);
        if(i == null || i <= 0){
            throw new RuntimeException("添加订单失败");
        } else {
//            for(CartDetail cartDetail : cartDetailList){
//                //totalPrice += (cartDetail.getDishesCount()*cartDetail.getDishes().getPrice());
//                orderDetailService.updateOrder();
//            }
            return i;
        }
    }
}
