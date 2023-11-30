package com.fivemin.mzpc.data.dto;

import com.fivemin.mzpc.data.entity.Food;
import com.fivemin.mzpc.data.entity.Members;
import lombok.Data;

@Data
public class CartDto {

    private Long idx;
    private String code;
    private String payments;
    private boolean buyCheck = false;
    private Food food;
    private Members member;

    // private String name; = foodName => food
    // private Integer price; = foodPrice => food
    // private boolean topping = false; = ifTopping => food
    // private String storeName; cart -> food -> category -> store
}