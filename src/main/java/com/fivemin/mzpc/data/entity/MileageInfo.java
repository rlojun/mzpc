package com.fivemin.mzpc.data.entity;

import com.fivemin.mzpc.data.entity.base.BaseCreateEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
public class MileageInfo extends BaseCreateEntity {

    //시간 추가 목록 index
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_purchase_idx")
    private Long idx;

    //시간 추가 목록 일련번호
    @Column(name = "time_purchase_code", nullable = false, unique = true)
    private String code;

    //적립 마일리지
    @Column(name = "save_mileage")
    private Integer save;
    
    //사용한 마일리지
    @Column(name = "use_mileage")
    private Integer use;

    public MileageInfo(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'L'HHMMyyyymmddss");
        this.code = currentDateTime.format(formatter);
    }

    @ManyToOne
    @JoinColumn(name = "member_idx",nullable = false)
    private Members members;

    @ManyToOne
    @JoinColumn(name = "time_idx", nullable = false)
    private Times times;
}
