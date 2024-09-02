package com.zqu.ordersystem.maper;

import com.zqu.ordersystem.pojo.Carts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartMapperTest {

    @Autowired
    CartMapper cartMapper;

    @Test
    void selectByUserId() {
        List<Carts> cartsList = cartMapper.selectByUserId(1);
        for(Carts cart : cartsList){
            System.out.println(cart);
        }
    }

    @Test
    void selectByOneUserId() {
        Carts carts = cartMapper.selectByOneUserId(1);
        System.out.println(carts);
    }
}