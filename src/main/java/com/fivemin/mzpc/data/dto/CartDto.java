package com.fivemin.mzpc.data.dto;

import com.fivemin.mzpc.data.entity.Food;
import com.fivemin.mzpc.data.entity.Members;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartDto {

    private Long idx;
    private String code;
    private String payments;

    private List<Food> food;

    private Integer cartPrice;

    private Members member;
    private Long memberIdx;

    private String storeName;
}