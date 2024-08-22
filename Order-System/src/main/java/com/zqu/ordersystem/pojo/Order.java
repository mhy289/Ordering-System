package com.zqu.ordersystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;
    private String orderName;
    //订单详情
    private String orderDetail;
    private Integer userId;
    // 1为已下单，2为已支付，3为已完成
    private Integer status;
    // 下单时间
    private Date orderTime;
    //总金额
    private Double totalPrice;
    //备注
    private String remark;
}
