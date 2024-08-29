package com.zqu.ordersystem.service;

import com.zqu.ordersystem.pojo.CartDetail;

import java.util.List;

public interface CartDetailService {

    List<CartDetail> queryBycartId(Integer id);
}
