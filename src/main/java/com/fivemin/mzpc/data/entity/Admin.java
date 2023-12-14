package com.fivemin.mzpc.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@Table
public class Admin {

    //관리자 index
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_idx")
    private Long idx;

    // 관리자 일련번호
    @Column(name = "admin_code",nullable = false, length = 15,unique = true)
    private String code;

    //관리자 아이디
    @Column(name = "admin_id",nullable = false,length = 20,unique = true)
    private String id;
    
    //관리자 비밀번호
    @Column(name = "admin_pw",nullable = false, length = 32)
    private String pw;

    //관리자 이름
    @Column(name = "admin_name", nullable = false, length = 50)
    private String name;

    //관리자 주민등록 번호
    @Column(name = "admin_ssn",nullable = false, length = 14)
    @Pattern(regexp = "^(\\d{6}-[1-4]\\d{6})$", message="올바른 주민등록번호 형식이여야 합니다.")
    private String ssn;

    //관리자 이메일
    @Column(name = "admin_email",nullable = false, length = 100)
    //@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$",message = "올바른 이메일 형식이여야 합니다.")
    @Email(message = "올바른 이메일 형식이여야 합니다.")
    private String email;

    //관리자 휴대폰 번호
    @Column(name = "phone_number",nullable = false, length = 13)
    @Pattern(regexp = "^[0-9]{3}-[0-9]{4}-[0-9]{4}$",message = "올바른 휴대폰 형식이어야 합니다.")
    private String number;

    @OneToOne
    @JoinColumn(name = "store_idx", nullable = false)
    private Store store;
}
