package com.fivemin.mzpc.data.dto;

import lombok.Data;

@Data
public class CartDto {

    private Long idx;
    private String code;
    private String payments;
    private boolean buyCheck;

}