package com.zqu.ordersystem.service;

import com.zqu.ordersystem.pojo.CartDetail;
import com.zqu.ordersystem.pojo.Carts;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CartDetailService {

    List<CartDetail> queryBycartId(Integer id);

    @Transactional
    Integer addCart(CartDetail cartDetail);

    List<CartDetail> queryCartList(List<Carts> cartList);

    @Transactional
    Integer reduceCart(Integer userId, Integer dishesId);

    @Transactional
    Integer reduceAllCart(Integer userId, List<CartDetail> cartDetailList);

    Double getTotalPrice(List<CartDetail> cartDetailList);
}
