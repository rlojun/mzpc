package com.fivemin.mzpc.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Favorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorites_idx")
    private Long favoritesIdx;

    
    //즐겨찾기 일련번호
    @Column(nullable = false, unique = true)
    private String favoritesCode;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Members members;

    @ManyToOne
    @JoinColumn(name = "food_idx",nullable = false)
    private Food food;

}
