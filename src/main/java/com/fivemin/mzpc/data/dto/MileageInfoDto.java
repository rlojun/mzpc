package com.fivemin.mzpc.data.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MileageInfoDto {
    private Long idx;
    private String code;
    private Integer save;
    private Integer use;
    private MembersDto membersDto;
    private TimeDto timeDto;
}
