package com.fivemin.mzpc.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Random;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
        this.code = generateUniqueCode();
    }

    public String generateUniqueCode() {
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

    //결제 방식 ------- payment로 바뀌자
    @Column(name = "payments", length = 15)
    private String payments;

    @OneToOne
    @JoinColumn(name = "member_idx", nullable = false)
    private Members members;

}
