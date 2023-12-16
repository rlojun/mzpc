package com.fivemin.mzpc.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    private String content;

    private String sender;

    private String Receiver;

    private Long pcNum;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime chatDate;

    @OneToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Members members;

    private String time;

    public void setTime(String time) {
        this.time = time;  // 시간을 설정하는 setTime 메소드
    }

}
