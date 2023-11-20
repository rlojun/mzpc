package com.fivemin.mzpc.data.dto;

import com.fivemin.mzpc.data.entity.Admin;
import com.fivemin.mzpc.data.entity.Category;
import lombok.Data;

@Data
public class FoodDTO {

    private Long idx;
    private String code;
    private String name;
    private String picture;
    private Integer price;
    private String description;
    private Integer stock;
    private Category category;
    private Admin admin;

}
