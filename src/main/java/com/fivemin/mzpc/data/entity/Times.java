package com.fivemin.mzpc.data.entity;

import com.fivemin.mzpc.data.entity.base.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
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
    private Long addTime;

    //가격
    @Column(name = "time_price", nullable = false)
    private Integer price;

    //저장 뮤무
    @Column(name ="save", nullable = false)
    private boolean save;

    @ManyToOne
    @JoinColumn(name = "store_idx",nullable = false)
    private Store store;

    // Duration으로 변환하는 메서드
    public Duration getAddTimeAsDuration() {
        return Duration.ofSeconds(addTime);
    }

    public Times (){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'M'HHMMyyyymmddss");
        this.code = currentDateTime.format(formatter);
    }

//    // Duration 덧셈 메서드 (아직 사용 x, 추후 사용 예정)
//    public void addToAddTime(Duration additionalDuration) {
//        this.addTime += additionalDuration.getSeconds();
//    }
//
//    // Duration 뺄셈 메서드 (아직 사용 x, 추후 사용 예정)
//    public void subtractFromAddTime(Duration subtractedDuration) {
//        this.addTime -= subtractedDuration.getSeconds();
//    }

}