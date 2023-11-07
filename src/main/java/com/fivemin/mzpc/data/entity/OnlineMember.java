package com.fivemin.mzpc.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class OnlineMember {
    @Id
    private Long olineMemberId;

    @CreatedDate
    private LocalDateTime startTime;

    @OneToOne
    @JoinColumn(name="member_id")
    private Members members;
}
