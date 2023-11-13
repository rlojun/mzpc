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
    private Long categoryIdx;

    //카테고리 일련번호
    @Column(nullable = false, unique = true)
    private String categoryCode;
    
    //카테고리 이름
    @Column(nullable = false, length = 30)
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "admin_idx",nullable = false)
    private Admin admin;

}
