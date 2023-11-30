package com.fivemin.mzpc.data.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodDto {

    private Long idx;
    private String code;
    private String name;
    private String picture;
    private Integer price;
    private String description;
    private Integer stock;
    private boolean topping;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private CategoryDto categoryDto;
}