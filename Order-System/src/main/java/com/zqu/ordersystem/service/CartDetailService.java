package com.zqu.ordersystem.service;

import com.zqu.ordersystem.pojo.CartDetail;
import com.zqu.ordersystem.pojo.Carts;

import java.util.List;

public interface CartDetailService {

    List<CartDetail> queryBycartId(Integer id);

    Integer addCart(CartDetail cartDetail);

    List<CartDetail> queryCartList(List<Carts> cartList);

    Integer reduceCart(Integer userId, Integer dishesId);

    Integer reduceAllCart(Integer userId, List<CartDetail> cartDetailList);

}
