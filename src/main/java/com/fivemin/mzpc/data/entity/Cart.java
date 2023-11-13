package com.fivemin.mzpc.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Cart {

    //장바구니 index
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_idx")
    private Long cartIdx;

    // 장바구니 일련번호
    @Column(nullable = false, unique = true)
    private String cartCode;

    //결제 방식
    @Column(nullable = false, length = 15)
    private String payments;

    // 주문할 상품 선택 여부
    private boolean buyCheck;

    @ManyToOne
    @JoinColumn(name = "food_idx", nullable = false)
    private Food food;

    @ManyToOne
    @JoinColumn(name = "member_idx", nullable = false)
    private Members members;

}
