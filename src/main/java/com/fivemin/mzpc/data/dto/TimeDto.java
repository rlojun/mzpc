package com.fivemin.mzpc.data.dto;

import com.fivemin.mzpc.data.entity.Times;
import lombok.*;

import java.time.Duration;

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
    private Duration addTime;
    private boolean save;
    // private TimeDto timeDto;
}
