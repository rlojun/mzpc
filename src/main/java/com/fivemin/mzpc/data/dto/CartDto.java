package com.fivemin.mzpc.data.dto;

import com.fivemin.mzpc.data.entity.Food;
import com.fivemin.mzpc.data.entity.Members;
import lombok.Data;

@Data
public class CartDto {

    private Long idx;
    private String code;
    private String payments;

    private Food food;
    private String cartName;
    private Integer cartPrice;

    private Members member;
    private Long memberIdx;

    private String storeName;
}