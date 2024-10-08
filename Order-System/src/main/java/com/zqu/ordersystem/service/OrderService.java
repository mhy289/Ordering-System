package com.zqu.ordersystem.service;

import com.zqu.ordersystem.pojo.CartDetail;
import com.zqu.ordersystem.pojo.Order;
import com.zqu.ordersystem.pojo.PageItem;
import com.zqu.ordersystem.pojo.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface OrderService {
    //查询所有订单
    List<Order> queryAllOrder();

    //查询一个订单
    Order queryOneOrder(Integer orderId);

    //分页查询
    PageItem<Order> queryPage(Integer current, Integer size);

    //条件分页查询
    PageItem<Order> queryConditionPage(Order order, Integer current, Integer size);

    //添加一个订单
    @Transactional
    Integer addOrder(Order order);

    //修改一个订单
    @Transactional
    Integer updateOrder(Order order);

    //删除一个订单
    @Transactional
    Integer deleteOrder(Integer orderId);

    @Transactional
    //根据购物车添加订单
    Integer addOrderByCart(Integer userId, List<CartDetail> cartDetailList);
}
