package com.zqu.ordersystem.service.impl;

import com.zqu.ordersystem.maper.CartDetailMapper;
import com.zqu.ordersystem.maper.CartMapper;
import com.zqu.ordersystem.myexception.BusinessException;
import com.zqu.ordersystem.myexception.ExceptionType;
import com.zqu.ordersystem.pojo.CartDetail;
import com.zqu.ordersystem.pojo.Carts;
import com.zqu.ordersystem.pojo.Dishes;
import com.zqu.ordersystem.service.CartService;
import com.zqu.ordersystem.service.DishesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    DishesService dishesService;

    @Autowired
    CartMapper cartMapper;

    @Autowired
    CartDetailMapper cartDetailMapper;

    @Override
    public Carts addToCart(Integer userId, Integer dishesId) {
        //判断菜品是否存在
        Dishes dishes = dishesService.queryDishById(dishesId);
        if (dishes == null) {
            throw new BusinessException(ExceptionType.DISH_NOT_FOUND,"该菜品不存在");
        }
        // 加入购物车
        Carts carts = findOrCreateCartForUser(userId);

        addProductToCart(carts.getId(), dishesId);
        return carts;

    }

    private void addProductToCart(Integer cartId, Integer dishesId) {
        Carts cart = cartMapper.selectById(cartId);
        log.debug("cartId is {}", cartId);
        if(cart == null) {
            log.debug("cart is {}",cart);
            throw new BusinessException(ExceptionType.SERVER_ERROR,"查询购物车失败");
        }
        //查看购物车是否存在
        CartDetail existingDetail = cartDetailMapper.findByCartIdAndDishesId(cart.getUserId(), dishesId);

        if(existingDetail != null){
            //existingDetail.setDishesCount(existingDetail.getDishesCount()+1);
            cartDetailMapper.updateCartDetail(existingDetail);
        } else {
            CartDetail cartDetail = new CartDetail();
            cartDetail.setCartId(cartId);
            cartDetail.setDishesId(dishesId);
            //cartDetail.setDishesCount(1);
            Integer insert = cartDetailMapper.insert(cartDetail);
            log.debug("cartDetail is {}  insert is {}", cartDetail,insert);
            if (insert == null || insert <= 0){
                throw new BusinessException(ExceptionType.SERVER_ERROR,"添加到购物车失败2");
            }
        }
    }

    @Override
    public Carts findOrCreateCartForUser(Integer userId) {
        Carts carts = cartMapper.selectById(userId);
        if (carts == null){
            carts = new Carts();
            carts.setUserId(userId);
            Integer insert = cartMapper.insert(carts);
            log.debug("insert is {}", insert);
            if (insert == null || insert <= 0 || carts.getId() == null){
                throw new BusinessException(ExceptionType.SERVER_ERROR,"添加购物车失败1");
            }
        }
        return carts;
    }

    //查看购物车
    @Override
    public List<Carts> getCartList(Integer userId) {
        return cartMapper.selectByUserId(userId);
    }

    @Override
    public Carts querycartById(Integer id) {
        return cartMapper.selectById(id);
    }
}
