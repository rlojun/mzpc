package com.fivemin.mzpc.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
-기능
현재 로그인 한 유저들의 목록
시작시간, 및 남은시간 표기
사용자가 qna chatting 으로 문의 하면 이름 옆에 표시
사용자 와의 qna chatting 기능 (사용자 상세보기, 사용자 정보 도 표기)
 */
@Controller
@RequestMapping("/{adminId}/members")
public class MemberController {

    // 현재 로그인 한 유저들의 목록
    @GetMapping("/listMember")
    public String listMember() {

        return "admin/listMember";
    }

    // 사용자 상세보기, 사용자 정보 도 표기
    @GetMapping("/detailMember/{memberId}")
    public String detailMember(@PathVariable String memberId) {

        return "admin/detailMember";
    }

    // 사용자가 qna chatting 으로 문의 하면 이름 옆에 표시
    @GetMapping("/chat")
    public String chat() {

        return "admin/members/chat";
    }

    // 사용자 와의 qna chatting 기능
    @PostMapping("/chat/{memberId}")
    public String chat(@PathVariable String memberId, @RequestParam String message) {

        return "redirect:/{adminId}/members/chat";
    }
}
