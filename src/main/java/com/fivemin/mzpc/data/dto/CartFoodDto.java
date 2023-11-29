package com.fivemin.mzpc.data.dto;

import com.fivemin.mzpc.data.entity.Food;
import lombok.Data;

@Data
public class CartFoodDto {

    private Long idx;
    private String code;
    private String name;
    private String picture;
    private Integer price;
    private String description;
    private Integer stock;
    private boolean topping = false;
    private String storeName;
    private Food food;
}