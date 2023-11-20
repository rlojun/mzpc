package com.fivemin.mzpc.data.dto;

// import com.fivemin.mzpc.data.entity.Topping;
import lombok.Data;

@Data
public class CartDto {

    private Long idx;
    private String code;
    private String payments;
    private boolean buyCheck;
//     private Topping topping;

}
