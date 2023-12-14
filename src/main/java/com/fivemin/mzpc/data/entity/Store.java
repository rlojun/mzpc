package com.fivemin.mzpc.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Store {

    @Id
    @Column(name = "store_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "store_code", nullable = false, unique = true,length = 15)
    private String code;

    @Column(name = "store_name", nullable = false, length = 20)
    private String name;

}
