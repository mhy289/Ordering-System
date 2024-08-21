package com.zqu.ordersystem.controller;

import com.zqu.ordersystem.pojo.Dishes;
import com.zqu.ordersystem.pojo.Result;
import com.zqu.ordersystem.service.DishesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
public class DishesController {

    @Autowired
    private DishesService dishesService;

    // 获取所有菜品
    @GetMapping("/dishes")
    public Result getAllDishes() {
        List<Dishes> dishes = dishesService.queryAllDishes();
        return new Result(dishes,"查询成功",200);
    }

    //获取单个菜品
    @GetMapping("/dish/{id}")
    public Result getDishById(@PathVariable Integer id) {
        Dishes dishes = dishesService.queryDishById(id);
        return new Result(dishes,"查询成功",200);
    }

    // 删除单个菜品
    @DeleteMapping("/dish/{id}")
    public Result deleteDish(@PathVariable Integer id) {
        Integer del = dishesService.deleteDishById(id);
        if(del == null || del <= 0){
            log.error("删除菜品失败, id: {}", id);
            return new Result(null,"删除菜品失败",401);
        }
        return new Result(null,"删除菜品成功",200);
    }

    // 创建单个菜品
    @PostMapping("/dish")
    public Result createDish(@RequestBody Dishes dishes) {
        Integer add = dishesService.addDish(dishes);
        if(add == null || add <= 0){
            log.error("创建菜品失败, dishes: {}", dishes);
            return new Result(null,"创建菜品失败",401);
        }
        return new Result(dishes,"创建菜品成功",200);
    }

    // 更新单个菜品
    @PutMapping("/dish/{id}")
    public Result updateDish(@PathVariable Integer id, @RequestBody Dishes dishes) {
        dishes.setId(id);
        Integer update = dishesService.updateDish(dishes);
        if(update == null || update <= 0){
            log.error("更新菜品失败, dishes: {}", dishes);
            return new Result(null,"更新菜品失败",401);
        }
        return new Result(dishes,"更新菜品成功",200);
    }
}
