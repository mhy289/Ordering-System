package com.zqu.ordersystem.maper;

import com.zqu.ordersystem.pojo.Carts;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CartMapper {


    @Select("select * from carts where user_id = #{userId}")
    Carts selectById(Integer userId);

    @Insert("insert into carts values (null, #{userId})")
    Integer insert(Carts carts);

    @Select("select * from carts")
    List<Carts> selectAll();
}
