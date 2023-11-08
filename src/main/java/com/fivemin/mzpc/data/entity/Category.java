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
    @Column(name = "category_id", length = 20)
    private Long categoryId;

    //카테고리 이름
    @Column(nullable = false, length = 30)
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "admin_id",nullable = false)
    private Admin admin;

}
