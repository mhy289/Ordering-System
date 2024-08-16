package com.zqu.maper;

import com.zqu.pojo.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OrderMapper {
    //查询所有订单
    @Select("select * from orders")
    List<Order> selectAllOrders();

    //查询一个订单
    @Select("select * from orders where id = #{id}")
    Order selectOrderById(Integer id);

    //添加一个订单
    @Insert("insert into orders values(null, #{orderName}, #{orderDetail}, #{userId}, #{dishesId}, #{status}, #{orderTime})")
    int addOrder(Order order);

    //删除一个订单
    @Delete("delete from orders where id = #{id}")
    int deleteOrderById(Integer id);

    //修改一个订单
    @Update("update orders set orderName = #{orderName}, orderDetail = #{orderDetail}, userId = #{userId}, dishesId = #{dishesId}, status = #{status}, orderTime = #{orderTime} where id = #{id}")
    int updateOrder(Order order);
}
