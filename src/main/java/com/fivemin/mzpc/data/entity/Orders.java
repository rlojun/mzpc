package com.fivemin.mzpc.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Orders {

    //주문 index
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_idx")
    private Long idx;

    @Column(name = "orders_code",nullable = false,unique = true)
    private String code;
    //조리 여부
    @Column(name = "cook_complete",nullable = false)
    private boolean cookComplete=false;

    //결제 여부
    @Column(name = "purchase_status",nullable = false)
    private boolean purchaseStatus=false;

    @Column(name = "payment", length = 15, nullable = false)
    private String payment;

    @OneToOne
    @JoinColumn(name = "cart_idx", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "store_idx",nullable = false)
    private Store store;

}
