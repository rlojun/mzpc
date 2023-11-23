package com.fivemin.mzpc.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuDto {

    private final Long idx;
    private final String code;
    private final String name;
    private final String picture;
    private final Integer price;
    private final String description;
    private final Integer stock;
    private final boolean topping;

    private Long categoryIdx;
    private String categoryCode;
    private String categoryName;

}
