package com.fivemin.mzpc.data.entity;

import com.fivemin.mzpc.data.entity.base.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Food extends BaseTimeEntity {
    //음식 Index
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_idx")
    private Long idx;

    //음식 일련번호
    @Column(name = "food_code",nullable = false, unique = true)
    private String code;

    //음식 이름
    @Column(name = "food_name", length = 30)
    private String name;

    //음식 사진
    @Column(name = "picture")
    private String picture;

    //음식 가격
    @Column(name = "food_price")
    private Integer price;

    //음식 설명
    @Column(name = "description")
    private String description;

    //음식 재고
    @Column(name = "stock")
    private Integer stock;

    @Column(name = "topping")
    private boolean topping;

    @ManyToOne
    @JoinColumn(name = "category_idx",nullable = false)
    public Category category;

}
