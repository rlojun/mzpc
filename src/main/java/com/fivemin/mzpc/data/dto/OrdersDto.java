package com.fivemin.mzpc.data.dto;

import com.fivemin.mzpc.data.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDto {

    private Long idx;
    private String code;
    private boolean cookComplete;
    private boolean purchaseStatus;
    private String payment;
    private String note;
    private Integer totalCost;
    private List<Cart> orderedFoods;
    private StoreDto storeDto;
    private List<CartDto> cartDtos;
}