    package com.fivemin.mzpc.controller.chat;

    import com.fivemin.mzpc.data.entity.Members;
    import com.fivemin.mzpc.service.members.MemberService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;

    import java.lang.reflect.Member;
    import java.security.Principal;
    import java.util.List;

    @Controller
    public class MemberChatController {

        @Autowired
        private MemberService memberService;

        @GetMapping("/members/{storeName}/chat")
        public String chat(@PathVariable String storeName, Model model, Principal principal) {
            model.addAttribute("storeName", storeName);
            if (principal != null) {
                String id = principal.getName(); // 현재 로그인한 사용자의 ID 가져오기
                model.addAttribute("memberId", id); // 사용자 ID를 Model 객체에 추가
                Members member = memberService.getMemberById(id); // ID를 사용하여 Members 엔티티 가져오기
                if (member != null) {
                    String username = member.getName(); // Members 엔티티에서 사용자 이름 가져오기
                    String memberId = member.getId();
                    model.addAttribute("memberId", memberId);
                    model.addAttribute("username", username); // 사용자 이름을 Model 객체에 추가
                }
            }
            return "members/chat/chat";
        }


        @GetMapping("/detailMember/{id}")
        public String getMemberDetail(@PathVariable("id") String id, Model model) {
            Members member = memberService.getMemberById(id);
            model.addAttribute("member", member);
            return "admin/chat/detailMember";       // detailMember.html 페이지 반환
        }

        @GetMapping("/listMember")
        public String listMember(Model model) {
            List<Members> members = memberService.getAllMembers();
            model.addAttribute("members", members);
            return "admin/chat/listMember";         // listMember.html 페이지 반환
        }
    }




