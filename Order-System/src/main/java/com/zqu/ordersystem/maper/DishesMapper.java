package com.zqu.ordersystem.maper;

import com.zqu.ordersystem.pojo.Dishes;
import com.zqu.ordersystem.pojo.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DishesMapper {
    //查询所有菜品
    @Select("select * from dishes")
    public List<Dishes> getAllDishes();

    //根据id查询单个菜品
    @Select("select * from dishes where id = #{id}")
    public Dishes getDishesById(Integer id);

    //添加新菜品
    @Insert("insert into dishes values(null, #{dishesName}, #{price}, #{sales}, #{description}, #{recommend}, #{imgUrl}, #{status}, #{categoryId})")
    public Integer addDishes(Dishes dishes);

    //修改菜品
    @Update("update dishes set dishes_name = #{dishesName}, price = #{price}, description = #{description}, recommend = #{recommend}, img_url = #{imgUrl}, status = #{status}, category_id = #{ccategoryId} where id = #{id}")
    public Integer updateDishes(Dishes dishes);

    //删除菜品
    @Delete("delete from dishes where id = #{id}")
    public Integer deleteDishes(Integer id);

    // 按条件查询菜品
    @Select("select * from dishes where id = #{id} and dishes_name like '%${dishesName}%' and category_id = #{categoryId}")
    List<Dishes> selectByCondition(Dishes dishes);

    @Select("select * from dishes where recommend = #{cod} and category_id = #{index}")
    List<Dishes> queryByCod(Integer cod, Integer index);
}
