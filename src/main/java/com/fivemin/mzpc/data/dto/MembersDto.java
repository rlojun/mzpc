package com.fivemin.mzpc.data.dto;

import com.fivemin.mzpc.data.entity.MileageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Builder
public class MembersDto {
    private Long idx;
    private String code;
    private String id;
    private String pw;
    private String name;
    private String ssn;
    private String number;
    private String email;
    private String address;
    private LocalDateTime startTime;
    private LocalTime remainingTime;
    private LocalTime loginRemainingTime;
    private int mileage;
    private List<MileageInfoDto> mileageInfoDtoList;
    private StoreDto storeDto;
}
