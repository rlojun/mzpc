package com.fivemin.mzpc.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TimePurchase {

    //시간 추가 목록 index
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_purchase_id")
    private Long timePurchaseId;

    //사용한 마일리지
    private Integer useMileage;

    @ManyToOne
    @JoinColumn(name = "member_id",nullable = false)
    private Members members;

    @ManyToOne
    @JoinColumn(name = "time_id", nullable = false)
    private Times times;
}
