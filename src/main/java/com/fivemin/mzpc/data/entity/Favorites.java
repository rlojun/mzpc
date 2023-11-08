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
    @Column(name = "favorites_id")
    private Long favoritesId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Members members;

    @ManyToOne
    @JoinColumn(name = "food_id",nullable = false)
    private Food food;

}
