package com.fivemin.mzpc.data.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.type.StringNVarcharType;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Members {

    // 사용자 아이디
    @Id
    @Column(name = "member_idx")
    private String memberIdx;

    //사용자 일련번호
    @Column(nullable = false, unique = true)
    private String memberCode;

    //사용자 아이디
    @Column(nullable = false, unique = true, length = 20)
    private String memberId;
    
    // 사용자 비밀 번호
    @Column(nullable = false, length = 32)
    private String memberPw;

    // 사용자 이름
    @Column(nullable = false, length = 50)
    private String memberName;

    // 사용자 주민 번호
    @Column(nullable = false, length = 100)
    @Pattern(regexp = "^(\\d{6}-[1-4]\\d{6})$", message="올바른 주민등록번호 형식이여야 합니다.")
    private String memberSsn;

    // 사용자 핸드폰 번호
    @Column(nullable = false)
    @Pattern(regexp = "^[0-9]{3}-[0-9]{4}-[0-9]{4}$",message = "올바른 휴대폰 형식이어야 합니다.")
    private String phoneNumber;

    // 사용자 이메일
    @Column(nullable = false, length = 100)
    //@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$",message = "올바른 이메일 형식이여야 합니다.")
    @Email(message = "올바른 이메일 형식이여야 합니다.")
    private String memberEmail;

    // 주소
    private String address;

    // 잔여 시간
    @Column(nullable = false)
    private Duration remainingTime;

    // 잔여 마일리지
    @Column(nullable = false)
    private int mileage;

    // timepayment와 1:M  양방향 매핑
    @OneToMany(mappedBy = "members")
    @ToString.Exclude
    private List<TimePurchase> timepaymentList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "admin_idx", nullable = false)
    private Admin admin;
}