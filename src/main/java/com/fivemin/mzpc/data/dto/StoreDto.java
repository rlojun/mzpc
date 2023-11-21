package com.fivemin.mzpc.data.dto;

import lombok.*;

@Getter
@ToString
@Setter
@AllArgsConstructor
@Builder
public class StoreDto {
    private final Long idx;
    private final String code;
    private final String name;
}
