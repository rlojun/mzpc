package com.fivemin.mzpc.data.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartDto {

    private Long idx;
    private String code;
    private boolean orderComplete;
    private MembersDto membersDto;
    private FoodDto foodDto;
}