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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mileage_idx")
    private Long idx;

    @Column(name = "mileage_code", nullable = false, unique = true)
    private String code;

    //마일리지 사용
    @Column(name = "use_mileage")
    private Integer usePoint;

    //적립 마일리지
    @Column(name = "save_mileage")
    private Integer savePoint;

    //적립, 사용 날짜
    @Column(name = "mileage_date")
    private LocalDateTime pointDate;

    @OneToOne
    @JoinColumn(name = "time_purchase_idx", nullable = false)
    private TimePurchase timePurchase;
}
