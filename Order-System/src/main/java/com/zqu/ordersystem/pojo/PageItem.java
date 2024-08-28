package com.zqu.ordersystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageItem<T>{
    // 总共有多少条
    Long total;
    // 当前页的数据
    List<T> list;
}
