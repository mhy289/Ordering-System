package com.zqu.ordersystem.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zqu.ordersystem.maper.DishesMapper;
import com.zqu.ordersystem.myexception.BusinessException;
import com.zqu.ordersystem.myexception.ExceptionType;
import com.zqu.ordersystem.pojo.Dishes;
import com.zqu.ordersystem.pojo.Order;
import com.zqu.ordersystem.pojo.PageItem;
import com.zqu.ordersystem.service.DishesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DishesServiceImpl implements DishesService {

    @Autowired
    DishesMapper dishesMapper;

    @Override
    public List<Dishes> queryAllDishes() {
        return dishesMapper.getAllDishes();
    }

    @Override
    public Integer addDish(Dishes dishes) {
        Integer addDishes = dishesMapper.addDishes(dishes);
        if (addDishes == 0){
            throw new BusinessException(ExceptionType.DISH_ADD_FALSE,"添加菜品失败");
        }
        return addDishes;
    }

    @Override
    public Dishes queryDishById(Integer id) {
        return dishesMapper.getDishesById(id);
    }

    @Override
    public Integer updateDish(Dishes dishes) {
        log.debug("dishes is {}", dishes);
        Integer i = dishesMapper.updateDishes(dishes);
        if (i == 0){
            throw new BusinessException(ExceptionType.DISH_UPDATE_FALSE,"修改菜品失败");
        } else {
            log.info("修改菜品成功");
            return i;
        }
        //return dishesMapper.updateDishes(dishes);
    }

    @Override
    public Integer deleteDishById(Integer id) {
        return dishesMapper.deleteDishes(id);
    }

    @Override
    public PageItem<Dishes> queryPage(Integer current, Integer size) {
        PageHelper.startPage(current, size);
        List<Dishes> dishesList = dishesMapper.getAllDishes();
        long total = ((Page<Dishes>) dishesList).getTotal();
        return new PageItem<>(total, dishesList);
    }

    @Override
    public PageItem<Dishes> queryConditionPage (Dishes dishes, Integer current, Integer size) {
        PageHelper.startPage(current, size);
        List<Dishes> dishesList = dishesMapper.queryByName(dishes.getDishesName());
        log.debug("dishesList is {}",dishesList);
        Page<Dishes> info = (Page<Dishes>) dishesList;
        log.debug("info is {}",info);
        long total = info.getTotal();
        return new PageItem<>(total, dishesList);
    }

    @Override
    public List<Dishes> queryByCod(Integer cod, Integer index) {
        return dishesMapper.queryByCod(cod, index);
    }

    @Override
    public List<Dishes> queryDishByName(String dishesName) {
        return dishesMapper.queryByName(dishesName);
    }
}
