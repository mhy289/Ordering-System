package com.zqu.ordersystem.maper;

import com.zqu.ordersystem.pojo.Dishes;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DishesMapper {
    //查询所有菜品
    @Select("select * from Dishes")
    public List<Dishes> getAllDishes();

    //根据id查询单个菜品
    @Select("select * from Dishes where id = #{id}")
    public Dishes getDishesById(Integer id);

    //添加新菜品
    @Insert("insert into Dishes values(null, #{DishesName}, #{price}, #{description}, #{recommend})")
    public void addDishes(Dishes dishes);

    //修改菜品
    @Update("update Dishes set dishesName = #{dishesName}, price = #{price}, description = #{description}, recommend = #{recommend} where id = #{id}")
    public void updateDishes(Dishes dishes);

    //删除菜品
    @Delete("delete from Dishes where id = #{id}")
    public void deleteDishes(Integer id);
}
