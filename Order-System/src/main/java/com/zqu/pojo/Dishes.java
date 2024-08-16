package com.zqu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dishes {
    private Integer id;
    private String DishesName;
    private Double price;
    private String description;
    // 是否推荐
    private Boolean recommend;
}
