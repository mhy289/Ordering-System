package com.zqu.ordersystem.maper;

import com.zqu.ordersystem.pojo.CartDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartDetailMapper {

    // 查询购物车中的某一道菜
    @Select("SELECT * FROM cartdetail WHERE cart_id = #{cartId} AND dishes_id = #{dishesId}")
    CartDetail findByCartIdAndDishesId(Integer cartId, Integer dishesId);

    // 更新购物车中
    @Update("update cartdetail set  cart_id = #{cartId}, dishes_id = #{dishesId}, dishes_count = #{dishesCount} where id = #{id}")
    Integer updateCartDetail(CartDetail existingDetail);

    @Insert("insert into cartdetail values (null, #{cartId}, #{dishesId}, #{dishesCount})")
    Integer insert(CartDetail cartDetail);

    @Select("select * from cartdetail where cart_id = #{cartId}")
    List<CartDetail> selectByCartIds(Integer id);

    @Select("select * from cartdetail where cart_id = #{cartId} and dishes_id = #{dishesId}")
    CartDetail selectByCondtion(Integer cartId, Integer dishesId);

    @Select("select * from cartdetail where cart_id = #{cartId}")
    CartDetail selectByCartId(Integer cartId);

    @Delete("delete from cartdetail where cart_id = #{cartId} and dishes_id = #{dishesId}")
    Integer deleteByCartIds(Integer cartId, Integer dishesId);
}
