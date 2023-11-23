package com.fivemin.mzpc.data.dto;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeDto {

    private String code;
    private String name;
    private Integer price;
    private LocalTime addTime;
    private boolean save;
    // private TimeDto timeDto;
}
