package com.zqu.ordersystem.service.impl;

import com.zqu.ordersystem.maper.CartDetailMapper;
import com.zqu.ordersystem.pojo.CartDetail;
import com.zqu.ordersystem.service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDetailServiceImpl implements CartDetailService {

    @Autowired
    CartDetailMapper cartDetailMapper;

    @Override
    public List<CartDetail> queryBycartId(Integer id) {
        return cartDetailMapper.selectByCartId(id);
    }
}
