package com.fivemin.mzpc.data.dto;

import com.fivemin.mzpc.data.entity.Admin;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
@NoArgsConstructor
public class CategoryDto {
    private Long idx;
    private String code;
    private String name;
    private AdminDto adminDto;

    public CategoryDto(String name, AdminDto adminDto) {
        this.idx = null;
        this.code = makeCode();
        this.name = name;
        this.adminDto = adminDto;
    }

    private String makeCode(){
        LocalDateTime currentDateTime=LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'M'HHMMyyyymmddss");

        return currentDateTime.format(formatter);
    }

}
