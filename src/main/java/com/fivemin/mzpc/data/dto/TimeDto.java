package com.fivemin.mzpc.data.dto;

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

    // Duration >> Long
    public Long getAddTimeAsLong() {
        return addTime.getSeconds();
    }

    // Long >> Duration
    public void setAddTimeAsLong(Long seconds) {
        this.addTime = Duration.ofSeconds(seconds);
    }
}
