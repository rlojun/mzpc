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

//    public void setAddTime(LocalTime addTime) {
//        this.addTime = addTime;
//    }
//
//    public void getAddTime(LocalTime addTime){
//        this.addTime = addTime;
//    }

//    // Duration >> Long
//    public Long getAddTimeAsLong() {
//        return addTime.getSeconds();
//    }
//
//    // Long >> Duration
//    public void setAddTimeAsLong(Long seconds) {
//        this.addTime = Duration.ofSeconds(seconds);
//    }
}
