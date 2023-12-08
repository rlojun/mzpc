package com.fivemin.mzpc.data.entity;

import com.fivemin.mzpc.data.entity.base.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
public class Times extends BaseTimeEntity {

    //시간 index
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_idx")
    private Long idx;

    @Column(name = "time_code", nullable = false, unique = true)
    private String code;

    //시간 상품 명
    @Column(name = "time_name", nullable = false,length = 15)
    private String name;

    //추가 시간
    @Column(name = "add_time",nullable = false)
    @DateTimeFormat(pattern = "H:mm:ss")
    private LocalTime addTime;

    //가격
    @Column(name = "time_price", nullable = false)
    private Integer price;

    // 삭제 유무
    @Column(name ="check_delete", nullable = false)
    private boolean checkDelete = false;

    @ManyToOne
    @JoinColumn(name = "store_idx",nullable = false)
    private Store store;

    public Times (){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'M'HHMMyyyymmddss");
        this.code = currentDateTime.format(formatter);
    }

    public void setAddTime(LocalTime addTime) {
        this.addTime = addTime;
    }
}