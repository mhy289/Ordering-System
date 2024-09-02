package com.zqu.ordersystem.maper;

import com.zqu.ordersystem.pojo.Carts;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CartMapper {


    @Select("select * from carts where id = #{Id}")
    Carts selectById(Integer userId);

    @Insert("insert into carts values (null, #{userId})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    Integer insert(Carts carts);

    @Select("select * from carts")
    List<Carts> selectAll();

    @Select("select * from carts where user_id = #{userId}")
    List<Carts> selectByUserId(Integer userId);

    @Select("select * from carts where user_id = #{userId}")
    Carts selectByOneUserId(Integer userId);
}
