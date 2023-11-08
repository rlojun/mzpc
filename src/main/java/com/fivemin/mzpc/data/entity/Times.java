package com.fivemin.mzpc.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;

@Entity
@Getter
@Setter
public class Times {

    //시간 index
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_id")
    private Long timeId;

    //시간 상품 명
    @Column(nullable = false,length = 15)
    private String timeName;

    //추가 시간
    @Column(nullable = false)
    private Duration addTime;

    //가격
    @Column(nullable = false)
    private Integer timePrice;

    //저장 뮤무
    @Column(nullable = false)
    private boolean save;

    @ManyToOne
    @JoinColumn(name = "admin_id",nullable = false)
    private Admin admin;

}
