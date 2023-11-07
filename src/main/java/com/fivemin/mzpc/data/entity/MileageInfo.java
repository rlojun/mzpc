package com.fivemin.mzpc.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class MileageInfo {
    
    //마일리지 index
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mileageId;

    //마일리지 사용
    private Integer usePoint;

    //적립 마일리지
    private Integer savePoint;

    //적립, 사용 날짜
    private LocalDateTime pointDate;

    @OneToOne
    @JoinColumn(name = "timePurchaseId", nullable = false)
    private TimePurchase timePurchase;
}
