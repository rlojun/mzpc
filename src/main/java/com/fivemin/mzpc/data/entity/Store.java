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
    private Long storeIdx;

    @Column( nullable = false, unique = true)
    private Long storeCode;

    @Column(nullable = false, length = 20)
    private String storeName;

}
