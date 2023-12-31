package com.fivemin.mzpc.data.dto;

import lombok.*;

@Data
@ToString
@Builder
public class AdminDto {
    private Long idx;
    private String code;
    private String id;
    private String pw;
    private String name;
    private String ssn;
    private String email;
    private String number;
    private StoreDto storeDto;

}
