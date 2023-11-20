package com.fivemin.mzpc.data.dto;

import com.fivemin.mzpc.data.entity.Admin;
import lombok.Data;

@Data
public class CategoryDTO {

    private Long idx;
    private String code;
    private String name;
    private Admin admin;

}
