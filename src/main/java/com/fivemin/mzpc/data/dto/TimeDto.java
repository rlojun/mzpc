package com.fivemin.mzpc.data.dto;

import lombok.*;

import java.time.LocalTime;

@ToString
@Builder
@Getter
@Setter
public class TimeDto {
    private String idx;
    private String code;
    private String name;
    private Integer price;
    private LocalTime addTime;
    private boolean save;
    private StoreDto storeDto;
}
