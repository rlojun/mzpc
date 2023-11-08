package com.fivemin.mzpc.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Cart {

    //카테고리 index
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;

    //결제 방식
    @Column(nullable = false, length = 15)
    private String payments;

    // 주문할 상품 선택 여부
    private boolean buyCheck;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Members members;

}
