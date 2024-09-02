package com.zqu.ordersystem.service;

import com.zqu.ordersystem.pojo.Carts;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CartService {

    @Transactional
    Carts addToCart(Integer userId, Integer dishesId);

    @Transactional
    Carts findOrCreateCartForUser(Integer userId);

    List<Carts> getCartList(Integer userId);

    Carts querycartById(Integer id);

}
