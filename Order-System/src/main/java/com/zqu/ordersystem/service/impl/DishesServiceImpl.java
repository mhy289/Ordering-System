package com.zqu.ordersystem.service.impl;

import com.zqu.ordersystem.maper.DishesMapper;
import com.zqu.ordersystem.pojo.Dishes;
import com.zqu.ordersystem.service.DishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishesServiceImpl implements DishesService {

    @Autowired
    private DishesMapper dishesMapper;

    @Override
    public List<Dishes> queryAllDishes() {
        return dishesMapper.getAllDishes();
    }

    @Override
    public Integer addDish(Dishes dishes) {
        return dishesMapper.addDishes(dishes);
    }

    @Override
    public Dishes queryDishById(Integer id) {
        return dishesMapper.getDishesById(id);
    }

    @Override
    public Integer updateDish(Dishes dishes) {
        return dishesMapper.updateDishes(dishes);
    }

    @Override
    public Integer deleteDishById(Integer id) {
        return dishesMapper.deleteDishes(id);
    }
}
