package com.fivemin.mzpc.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity
public class Inquiry {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;  // 문의 제목
    private String content;  // 문의 내용

    @Column(name = "member_id")
    private String memberId;  // 문의를 보낸 회원의 ID

    @Column(name = "admin_id")
    private String adminId;  // 문의에 답변한 관리자의 ID

    private LocalDateTime createDate;  // 문의를 보낸 시간
    private LocalDateTime answerDate;  // 문의에 답변한 시간

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setAnswerDate(LocalDateTime answerDate) {
        this.answerDate = answerDate;
    }
}