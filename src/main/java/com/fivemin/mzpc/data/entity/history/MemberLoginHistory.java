package com.fivemin.mzpc.data.entity.history;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.fivemin.mzpc.data.entity.Members;
import com.fivemin.mzpc.data.entity.base.BaseCreateEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class MemberLoginHistory extends BaseCreateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_login_history_idx")
    private Long idx;

    //온라인 유무( false : 미사용 , true : 사용)
    @Column(name = "status", nullable = false)
    private boolean status = true;

//    // 사용한 시각
//    @CreatedDate
//    @Column(name = "start_time")
//    private LocalDateTime startTime;
//
//    // 끝낸 시각
//    @Column(name = "end_time")
//    private LocalDateTime endTime;

    @OneToOne
    @JoinColumn(name="member_idx", nullable = false)
    private Members members;
}
