package com.fivemin.mzpc.data.dto;

import com.fivemin.mzpc.data.entity.Admin;
import com.fivemin.mzpc.data.entity.Category;
import lombok.*;

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
    private final boolean topping;
    private final CategoryDto categoryDto;
}