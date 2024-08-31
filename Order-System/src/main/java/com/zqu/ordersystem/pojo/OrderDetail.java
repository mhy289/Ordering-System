package com.zqu.ordersystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    //订单详情
    private Integer id;
    // 所属订单
    private Integer orderId;
    // 所属商品
    private Integer dishesId;
    //菜品数量
    private Integer dishesCount;

    private Order order;

    private Dishes dishes;
}
