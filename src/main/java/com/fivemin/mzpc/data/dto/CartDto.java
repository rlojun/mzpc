package com.fivemin.mzpc.data.dto;

import com.fivemin.mzpc.data.entity.Food;
import com.fivemin.mzpc.data.entity.Members;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartDto {

    private Long idx;
    private String code;
    private MembersDto membersDto;
    private FoodDto foodDto;
}