package com.fivemin.mzpc.data.dto;

import com.fivemin.mzpc.data.entity.Food;
import com.fivemin.mzpc.data.entity.Members;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FavoritesDto {

    private Long idx;
    private String code;
    private Members members;
    private Food food;
}
