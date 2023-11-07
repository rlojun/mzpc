package com.fivemin.mzpc.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
public class Admin {

    //관리자 아이디
    @Id
    @Column(length = 20)
    private String adminId;

    //관리자 비밀번호
    @Column(nullable = false, length = 32)
    private String adminPw;

    //관리자 이름
    @Column(nullable = false, length = 50)
    private String adminName;

    //관리자 주민등록 번호
    @Column(nullable = false, length = 14)
    @Pattern(regexp = "^(\\d{6}-[1-4]\\d{6})$", message="올바른 주민등록번호 형식이여야 합니다.")
    private String adminSsn;

    //관리자 이메일
    @Column(nullable = false, length = 100)
    //@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$",message = "올바른 이메일 형식이여야 합니다.")
    @Email(message = "올바른 이메일 형식이여야 합니다.")
    private String adminEmail;

    //관리자 휴대폰 번호
    @Column(nullable = false, length = 13)
    @Pattern(regexp = "^[0-9]{3}-[0-9]{4}-[0-9]{4}$",message = "올바른 휴대폰 형식이어야 합니다.")
    private String phoneNumber;

    //업체명
    @Column(nullable = false, length = 20)
    private String storeName;

}
