package com.fivemin.mzpc.data.dto;

import com.fivemin.mzpc.data.entity.Category;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodDto {

    private Long idx;
    private String code;
    private String name;
    private String picture;
    private Integer price;
    private String description;
    private Integer stock;
    private boolean topping;
    private String storeName;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private CategoryDto categoryDto;
    private Category category;
    private String categoryCode;
    private String categoryName;
    private Long categoryIdx;
}