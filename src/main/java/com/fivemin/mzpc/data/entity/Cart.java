package com.fivemin.mzpc.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Cart {

    //장바구니 index
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_idx")
    private Long idx;

    // 장바구니 일련번호
    @Column(name = "cart_code",nullable = false, unique = true)
    private String code;

    @PrePersist
    protected void onCreate() {
        // Generate a unique code and set it before persisting the entity
        this.code = generateUniqueCode();
    }

    // You can implement a method to generate a unique code based on your requirements
    private String generateUniqueCode() {
        // Implement your logic to generate a unique code (e.g., using UUID)
        // For simplicity, using UUID here, but you can customize it as needed
        return UUID.randomUUID().toString();
    }

    //결제 방식
    @Column(name = "payments", length = 15)
    private String payments;

//    // 주문할 상품 선택 여부
//    @Column(name = "buy_check")
//    private boolean buyCheck = false;

    @OneToOne
    @JoinColumn(name = "member_idx", nullable = false)
    private Members members;

    @ManyToOne
    @JoinColumn(name = "food_idx", nullable = false)
    private Food food;

}
