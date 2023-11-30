package com.fivemin.mzpc.data.dto;

import com.fivemin.mzpc.data.entity.Category;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@Builder
public class FoodDto {

    private final Long idx;
    private final String code;
    private final String name;
    private final String picture;
    private final Integer price;
    private final String description;
    private final Integer stock;
    private final boolean topping = false;
    private String storeName;
    private final LocalDateTime createdAt;
    private final LocalDateTime updateAt;
    private final CategoryDto categoryDto;
    private final Category category;
    private final String categoryCode;
    private final String categoryName;
    private final Long categoryIdx;
}