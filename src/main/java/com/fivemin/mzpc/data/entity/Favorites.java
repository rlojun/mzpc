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
    private Long idx;

    
    //즐겨찾기 일련번호
    @Column(name = "favorites_code",nullable = false, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "member_idx", nullable = false)
    private Members members;

    @ManyToOne
    @JoinColumn(name = "food_idx",nullable = false)
    private Food food;

}
