package com.zqu.ordersystem.service;

import com.zqu.ordersystem.pojo.Carts;

import java.util.List;

public interface CartService {


    Carts addToCart(Integer userId, Integer dishesId);

    Carts findOrCreateCartForUser(Integer userId);

    List<Carts> getCartList(Integer userId);

}
