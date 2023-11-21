package com.fivemin.mzpc.data.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private Long idx;
    private String code;
    private String name;
    private StoreDto storeDto;
}