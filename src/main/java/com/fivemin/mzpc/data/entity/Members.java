package com.fivemin.mzpc.data.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private String idx;

    //사용자 일련번호
    @Column(name = "member_code",nullable = false, unique = true)
    private String code;

    //사용자 아이디
    @Column(name = "member_id",nullable = false, unique = true, length = 20)
    private String id;
    
    // 사용자 비밀 번호
    @Column(name = "member_pw",nullable = false, length = 32)
    private String pw;

    // 사용자 이름
    @Column(name = "member_name",nullable = false, length = 50)
    private String name;

    // 사용자 주민 번호
    @Column(name = "member_ssn",nullable = false, length = 100)
    @Pattern(regexp = "^(\\d{6}-[1-4]\\d{6})$", message="올바른 주민등록번호 형식이여야 합니다.")
    private String ssn;

    // 사용자 핸드폰 번호
    @Column(name = "phone_number", nullable = false)
    @Pattern(regexp = "^[0-9]{3}-[0-9]{4}-[0-9]{4}$",message = "올바른 휴대폰 형식이어야 합니다.")
    private String number;

    // 사용자 이메일
    @Column(name = "member_email",nullable = false, length = 100)
    //@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$",message = "올바른 이메일 형식이여야 합니다.")
    @Email(message = "올바른 이메일 형식이여야 합니다.")
    private String email;

    // 주소
    @Column(name = "address")
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