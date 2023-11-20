package com.fivemin.mzpc.data.dto;

import com.fivemin.mzpc.data.entity.Cart;
import lombok.Data;

@Data
public class OrdersDTO {

    private Long idx;
    private boolean cookComplete;
    private boolean purchaseStatus;
    private Cart cart;

}
