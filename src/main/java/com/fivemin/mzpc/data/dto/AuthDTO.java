package com.fivemin.mzpc.data.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class AuthDTO {

    // 사용자 아이디
    private String id;

    // 사용자 비밀번호
    private String pw;

    // 사용자 이름
    private String name;

    // 사용자 주민번호
    private String ssn;

    // 사용자 휴대폰 번호
    private String number;

    // 사용자 이메일
    private String email;

    // 사용자 주소 (Null 값 허용)
    private String address;

    // 업체명
    private String storeName;
}