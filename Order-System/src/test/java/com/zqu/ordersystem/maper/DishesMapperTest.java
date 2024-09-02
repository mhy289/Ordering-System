package com.zqu.ordersystem.maper;

import com.zqu.ordersystem.pojo.Dishes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DishesMapperTest {

    @Autowired
    DishesMapper dishesMapper;

    @Test
    void addDishes() {
        Dishes dishes = new Dishes();
        dishes.setDishesName("test");
        dishesMapper.addDishes(dishes);
        List<Dishes> allDishes = dishesMapper.getAllDishes();
        for (Dishes dst : allDishes) {
            System.out.println(dst);
        }
    }
}