package com.zqu.ordersystem.service;

import com.zqu.ordersystem.pojo.Dishes;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface DishesService {
    //查询所有菜品
    List<Dishes> queryAllDishes();

    @Transactional
    //添加新菜品
    Integer addDish(Dishes dishes);

    //根据id查询单个菜品
    Dishes queryDishById(Integer id);

    @Transactional
    //修改单个菜品
    Integer updateDish(Dishes dishes);

    @Transactional
    //删除单个菜品
    Integer deleteDishById(Integer id);
}
