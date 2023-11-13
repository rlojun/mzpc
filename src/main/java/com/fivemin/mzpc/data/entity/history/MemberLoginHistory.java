package com.fivemin.mzpc.data.entity.history;

import com.fivemin.mzpc.data.entity.Members;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class MemberLoginHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_login_history_idx")
    private Long idx;

    //온라인 유저 일련번호
    @Column(name = "member_login_history_code",nullable = false, unique = true)
    private String code;

    //온라인 유무( false : 미사용 , true : 사용)
    @Column(name = "status", nullable = false)
    private boolean status = true;

    // 사용한 시각
    @CreatedDate
    @Column(name = "start_time")
    private LocalDateTime startTime;
    
    // 끝낸 시각
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @OneToOne
    @JoinColumn(name="member_idx", nullable = false)
    private Members members;
}
