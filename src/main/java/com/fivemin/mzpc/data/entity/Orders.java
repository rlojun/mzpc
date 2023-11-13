package com.fivemin.mzpc.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Orders {

    //주문 index
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_idx")
    private Long orderIdx;

    //조리 여부
    @Column(nullable = false)
    private boolean cookComplete=false;

    //결제 여부
    @Column(nullable = false)
    private boolean purchaseStatus=false;

    @OneToOne
    @JoinColumn(name = "cart_idx", nullable = false)
    private Cart cart;
}
