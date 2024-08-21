package com.zqu.ordersystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dishes {
    private Integer id;
    private String dishesName;
    private Double price;
    private String description;
    // 是否推荐
    private Integer recommend;
    //图片
    private String imgUrl;
    //状态
    private Integer status;
    //分类
    private Integer categoryId;
}
