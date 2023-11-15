package com.fivemin.mzpc.data.entity;

import com.fivemin.mzpc.data.entity.base.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Topping extends BaseTimeEntity {

    // 토핑 index
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topping_idx")
    private Long idx;

    // 토핑 일련번호
    @Column(name = "topping_code",nullable = false, unique = true)
    private String code;


    // 토핑명
    @Column(name = "topping_name", nullable = false)
    private String name;

    // 토핑 가격
    @Column(name = "topping_price", nullable = false)
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "food_idx", nullable = false)
    private Food food;

}
