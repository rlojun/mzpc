package com.fivemin.mzpc.data.dto;

import com.fivemin.mzpc.data.entity.Cart;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrdersDto {

    private Long idx;
    private boolean cookComplete;
    private boolean purchaseStatus;
    private CartDto cartDto;
}