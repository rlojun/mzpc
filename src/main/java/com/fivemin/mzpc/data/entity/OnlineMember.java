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
    @Column(name = "oline_member_id")
    private Long olineMemberId;

    @CreatedDate
    private LocalDateTime startTime;

    @OneToOne
    @JoinColumn(name="member_id", nullable = false)
    private Members members;
}
