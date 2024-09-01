package com.zqu.ordersystem.service;

import com.zqu.ordersystem.pojo.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface OrderDetailService {

    //查询所有订单详情
    List<OrderDetail> queryAllOrder();

    //查询一个订单详情
    OrderDetail queryOneOrder(Integer orderId);

    //分页查询详情
    PageItem<OrderDetail> queryPage(Integer current, Integer size);

    //条件分页查询详情
    PageItem<OrderDetail> queryConditionPage(OrderDetail orderdetail, Integer current, Integer size);

    //添加一个订单详情
    @Transactional
    Integer addOrder(OrderDetail orderdetail);

    //修改一个订单详情
    @Transactional
    Integer updateOrder(OrderDetail orderdetail);

    //删除一个订单详情
    @Transactional
    Integer deleteOrder(Integer orderDetailId);

    @Transactional
    Integer addOrderByOrder(Order order, CartDetail cartDetail);
}
