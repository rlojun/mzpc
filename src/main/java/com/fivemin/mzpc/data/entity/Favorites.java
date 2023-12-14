package com.fivemin.mzpc.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Random;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Favorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorites_idx")
    private Long idx;

    //즐겨찾기 일련번호
    @Column(name = "favorites_code",nullable = false, unique = true, length = 15)
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

    @ManyToOne
    @JoinColumn(name = "member_idx", nullable = false)
    private Members members;

    @ManyToOne
    @JoinColumn(name = "food_idx",nullable = false)
    private Food food;

}
