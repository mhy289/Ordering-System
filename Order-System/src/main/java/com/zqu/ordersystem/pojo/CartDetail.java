package com.zqu.ordersystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetail {
    private Integer id;
    private Integer cartId;
    private Integer dishesId;
    private Integer dishesCount;
    private Carts cart;
    private Dishes dishes;
}
