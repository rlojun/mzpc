package com.fivemin.mzpc.data.dto;

import lombok.*;

@Data
@Builder
public class CategoryDto {
    private Long idx;
    private String code;
    private String name;

}