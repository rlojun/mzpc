package com.fivemin.mzpc.controller.chat;

import com.fivemin.mzpc.data.entity.Members;
import com.fivemin.mzpc.service.members.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

//회원 정보 조회, 회원 상세 정보 보기, 채팅 등 회원 관련 기능을 담당하는 컨트롤러 << 관리자
@Controller
@RequestMapping("/{storeName}/members")
public class ChatMemberController {

    private final MemberService memberService;
    private String memberId;
    private Model model;

    public ChatMemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 현재 로그인 한 유저들의 목록
    @GetMapping("/listChatMember")
    public String listMember(Model model) {
        List<Members> members = memberService.getAllMembers();
        model.addAttribute("members", members);
        return "admin/chat/listMember";
    }

    @GetMapping("/member/detail/{memberId}")
    public String detailMember(@PathVariable("memberId") String memberId, Model model) {
        Members member = memberService.getMemberById(memberId);
        model.addAttribute("member", member);
        return "admin/detailMember";
    }

    @PostMapping("/chat")
    public String handleDetailMember(@RequestParam("memberId") String memberId, Model model) {
        model.addAttribute("memberId", memberId);
        return "admin/detailMember";
    }


    // 사용자가 qna chatting 으로 문의 하면 이름 옆에 표시
    @GetMapping("/chat")
    public String chat(Principal principal, Model model) {
        if (principal != null) {
            String memberId = principal.getName(); // 로그인한 사용자의 ID 가져오기
            model.addAttribute("memberId", memberId); // 사용자 ID를 모델에 추가
        }
        // 채팅 관련 로직 구현 필요
        return "admin/members/chat";
    }


    // 사용자 와의 qna chatting 기능
    @GetMapping("/admin/detailMember/{memberId}")
    public String qna(@PathVariable String memberId, Model model) {
        model.addAttribute("memberId", memberId);

        return "member/chat";  //  페이지로 이동
    }


}
