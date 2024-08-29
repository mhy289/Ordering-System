package com.zqu.ordersystem.maper;

import com.zqu.ordersystem.pojo.Order;
import com.zqu.ordersystem.pojo.OrderDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    //查询所有订单
    @Select("select * from orderdetail")
    List<OrderDetail> selectAllOrders();

    //查询一个订单
    @Select("select * from orderdetail where id = #{id}")
    OrderDetail selectOrderById(Integer id);

    //添加一个订单
    @Insert("insert into orderdetail values(null, #{orderId}, #{dishesId}, #{dishesCount})")
    int addOrder(OrderDetail orderdetail);

    //删除一个订单
    @Delete("delete from orderdetail where id = #{id}")
    int deleteOrderById(Integer id);

    //修改一个订单
    @Update("update orderdetail set order_id = #{orderId}, dishes_id = #{dishesId}, dishes_count = #{dishesCount} where id = #{id}")
    int updateOrder(OrderDetail orderdetail);

    // 按条件查询用户
    @Select("select * from orderdetail where id = #{id} and order_id = #{orderId} and dishes_id = #{dishesId}")
    List<OrderDetail> selectByCondition(OrderDetail orderdetail);
}
