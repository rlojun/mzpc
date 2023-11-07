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
    private Long FavoritesId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Members members;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Food food;

}
