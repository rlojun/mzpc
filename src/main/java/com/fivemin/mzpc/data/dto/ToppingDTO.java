package com.fivemin.mzpc.data.dto;

import com.fivemin.mzpc.data.entity.Food;
import lombok.Data;

@Data
public class ToppingDTO {

    private Long idx;
    private String code;
    private String name;
    private Integer price;
    private Food food;

}
