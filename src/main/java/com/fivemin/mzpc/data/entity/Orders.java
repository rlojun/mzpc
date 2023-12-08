package com.fivemin.mzpc.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @PrePersist
    protected void onCreate() {
        this.code = generateUniqueCode();
    }
    public static String generateUniqueCode() {
        int codeLength = 6;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < codeLength; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }

    //조리 여부
    @Column(name = "cook_complete",nullable = false)
    private boolean cookComplete=false;

    //결제 여부
    @Column(name = "purchase_status",nullable = false)
    private boolean purchaseStatus=false;

    @Column(name = "payment", length = 15, nullable = false)
    private String payment;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.PERSIST)
    private List<Cart> carts = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "store_idx",nullable = false)
    private Store store;

}
