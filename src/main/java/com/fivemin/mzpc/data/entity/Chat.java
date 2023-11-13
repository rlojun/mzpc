package com.fivemin.mzpc.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Chat {
    //좌석 번호로 PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pc_num")
    private Long pcNum;

    //메세지 유형(Enter,Talk만 들어갈 수 있음)
    @Enumerated(EnumType.STRING)
    @Column(length = 5, nullable = false)
    private MessageType type;

    //사용자가 보낸 채팅
    private String memberChat;

    //관리자가 보낸 채팅
    private String adminChat;

    //채팅한 날짜
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime chatDate;

    @OneToOne
    @JoinColumn(name = "member_idx", nullable = false)
    private Members members;

    private enum MessageType{
        ENTER,TALK
    }
}
