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
    @NonNull
    private String id;

    // 사용자 비밀번호
    @NonNull
    private String pw;

    // 사용자 이름
    @NonNull
    private String name;

    // 사용자 주민번호
    @NonNull
    @Pattern(regexp = "^(\\d{6}-[1-4]\\d{6})$", message="올바른 주민등록번호 형식이여야 합니다.")
    private String ssn;

    // 사용자 휴대폰 번호
    @NonNull
    @Pattern(regexp = "^[0-9]{3}-[0-9]{4}-[0-9]{4}$",message = "올바른 휴대폰 형식이어야 합니다.")
    private String number;

    // 사용자 이메일
    @NonNull
    @Email(message = "올바른 이메일 형식이여야 합니다.")
    private String email;

    // 사용자 주소 (Null 값 허용)
    private String address;

    // 업체명
    @NonNull
    private String storeName;
}