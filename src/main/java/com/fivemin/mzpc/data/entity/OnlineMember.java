package com.fivemin.mzpc.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class OnlineMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oline_member_idx")
    private Long olineMemberIdx;

    //온라인 유저 일련번호
    @Column(nullable = false, unique = true)
    private String onlineMemberCode;

    //온라인 유무
    @Column(nullable = false)
    private boolean status;

    @CreatedDate
    private LocalDateTime startTime;

    @OneToOne
    @JoinColumn(name="member_idx", nullable = false)
    private Members members;
}
