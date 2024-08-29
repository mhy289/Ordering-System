package com.zqu.ordersystem.maper;

import com.zqu.ordersystem.pojo.CartDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CartDetailMapper {

    // 查询购物车中的某一道菜
    @Select("SELECT * FROM cartdetail WHERE cart_id = #{cartId} AND dishes_id = #{dishesId}")
    CartDetail findByCartIdAndDishesId(Integer cartId, Integer dishesId);

    // 更新购物车中
    @Update("update cartdetail set  cart_id = #{cartId}, dishes_id = #{dishesId}, dishes_count = #{dishesCount} where id = #{id}")
    void updateCartDetail(CartDetail existingDetail);

    @Insert("insert into cartdetail values (null, #{cartId}, #{dishesId}, #{dishesCount})")
    Integer insert(CartDetail cartDetail);

    List<CartDetail> selectByCartId(Integer id);
}
