package com.zqu.ordersystem.service;

import com.zqu.ordersystem.pojo.Dishes;
import com.zqu.ordersystem.pojo.Order;
import com.zqu.ordersystem.pojo.PageItem;
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

    //分页查询
    PageItem<Dishes> queryPage(Integer current, Integer size);

    //条件分页查询
    PageItem<Dishes> queryConditionPage(Dishes dishes, Integer current, Integer size);

    List<Dishes> queryByCod(Integer cod, Integer index);

    List<Dishes> queryDishByName(String dishesName);

    List<Dishes> queryDishByCondition(Dishes dishes);
}
