package com.fivemin.mzpc.data.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StoreDto {
    private final Long idx;
    private final String code;
    private final String name;

    public StoreDto(Long idx, String code, String name) {
        this.idx = idx;
        this.code = code;
        this.name = name;
    }
}
