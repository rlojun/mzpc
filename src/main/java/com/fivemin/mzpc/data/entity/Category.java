package com.fivemin.mzpc.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Category{

    //카테고리 index
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_idx", length = 20)
    private Long idx;

    //카테고리 일련번호
    @Column(name = "category_code",nullable = false, unique = true)
    private String code;
    
    //카테고리 이름
    // 유니크 속성 추가하기
    @Column(name = "category_name",nullable = false, length = 30)
    private String name;

    @ManyToOne
    @JoinColumn(name = "store_idx",nullable = false)
    private Store store;

}
