package com.zqu.ordersystem.service.impl;

import com.zqu.ordersystem.maper.CartDetailMapper;
import com.zqu.ordersystem.maper.CartMapper;
import com.zqu.ordersystem.maper.DishesMapper;
import com.zqu.ordersystem.maper.UserMapper;
import com.zqu.ordersystem.myexception.BusinessException;
import com.zqu.ordersystem.myexception.ExceptionType;
import com.zqu.ordersystem.pojo.CartDetail;
import com.zqu.ordersystem.pojo.Carts;
import com.zqu.ordersystem.service.CartDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CartDetailServiceImpl implements CartDetailService {

    @Autowired
    CartDetailMapper cartDetailMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    DishesMapper dishMapper;

    @Autowired
    CartMapper cartMapper;

    @Override
    public List<CartDetail> queryBycartId(Integer id) {
        return cartDetailMapper.selectByCartIds(id);
    }

    @Override
    public Integer addCart(CartDetail cartDetail) {
        return cartDetailMapper.insert(cartDetail);
    }

    @Override
    public List<CartDetail> queryCartList(List<Carts> cartList) {
        List<CartDetail> cartDetailList = new ArrayList<>();
        for(Carts cart : cartList){
            List<CartDetail> cartDetails = cartDetailMapper.selectByCartIds(cart.getId());
            for(CartDetail cartDetail : cartDetails) {
                log.debug(" cartId is {}", cart.getId());
                if (cartDetail != null) {
                    cart.setUser(userMapper.getUserById(cart.getUserId()));
                    cartDetail.setCart(cart);
                    cartDetail.setDishes(dishMapper.getDishesById(cartDetail.getDishesId()));
                    cartDetailList.add(cartDetail);
                }
            }
            //throw new BusinessException(ExceptionType.CART_NOT_FOUND,"购物车找不到");
        }
        return cartDetailList;
    }

    @Override
    public Integer reduceCart(Integer userId, Integer dishesId) {
        // 查询购物车中是否有该菜品
        return cartDetailMapper.deleteByCartIds(cartMapper.selectByOneUserId(userId).getId(),dishesId);
    }

    @Override
    public Integer reduceAllCart(Integer userId, List<CartDetail> cartDetailList) {
        for(CartDetail cartDetail : cartDetailList){
            Integer i = cartDetailMapper.deleteByCartIds(cartMapper.selectByOneUserId(userId).getId(), cartDetail.getDishesId());
            if(i == 0){
                throw new BusinessException(ExceptionType.CART_NOT_FOUND,"购物车找不到");
            }
        }
        return 1;
    }
}
