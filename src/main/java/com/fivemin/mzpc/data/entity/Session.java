package com.fivemin.mzpc.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_idx")
    private Long idx;

    @Column(name = "session_code")
    private String code;

    @Column(name = "session_name")
    private String name;

    @Column
    private String stringValue;

    @Column
    private int intValue;

    @Column
    private LocalTime localTimeValue;
}
