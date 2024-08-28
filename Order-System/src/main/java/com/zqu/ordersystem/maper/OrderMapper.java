package com.zqu.ordersystem.maper;

import com.zqu.ordersystem.pojo.Order;
import com.zqu.ordersystem.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    //查询所有订单
    @Select("select * from orders")
    List<Order> selectAllOrders();

    //查询一个订单
    @Select("select * from orders where id = #{id}")
    Order selectOrderById(Integer id);

    //添加一个订单
    @Insert("insert into orders values(null, #{orderName}, #{orderDetail}, #{userId}, #{status}, #{orderTime}, #{totalPrice}, #{remark})")
    int addOrder(Order order);

    //删除一个订单
    @Delete("delete from orders where id = #{id}")
    int deleteOrderById(Integer id);

    //修改一个订单
    @Update("update orders set order_name = #{orderName}, order_detail = #{orderDetail}, user_id = #{userId}, status = #{status}, order_time = #{orderTime}, total_price = #{totalPrice}, remark = #{remark} where id = #{id}")
    int updateOrder(Order order);

    // 按条件查询用户
    @Select("select * from orders where id = #{id} and order_name like '%${orderName}%' and user_id = #{userId}")
    List<Order> selectByCondition(Order order);
}
