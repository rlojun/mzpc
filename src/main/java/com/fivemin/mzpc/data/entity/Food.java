package com.fivemin.mzpc.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Food {
    //음식 Index
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;

    //음식 이름
    @Column(length = 30)
    private String foodName;

    //음식 사진
    private String picture;

    //음식 가격
    private Integer foodPrice;

    //음식 설명
    private String description;

    //음식 재고
    private Integer stock;

    //토핑 판별
    private boolean topping = false;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "admin_id",nullable = false)
    private Admin admin;

}
