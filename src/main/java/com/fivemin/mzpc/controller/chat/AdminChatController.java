    package com.fivemin.mzpc.controller.chat;

    import com.fivemin.mzpc.data.entity.Members;
    import com.fivemin.mzpc.service.members.MemberService;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;

    import java.lang.reflect.Member;
    import java.util.List;

    @Controller
    public class AdminChatController {

        private final MemberService memberService;

        public AdminChatController(MemberService memberService) {
            this.memberService = memberService;
        }

        @GetMapping("/admin/{storeCode}/listMember")
        public String chat(@PathVariable String storeCode, Model model) {
            List<Members> members = memberService.getAllMembers();
            model.addAttribute("members", members); // 이 부분이 추가되었습니다.
            model.addAttribute("storeCode", storeCode);
            return "admin/chat/listMember";
        }

        @GetMapping("/admin/{storeCode}/detailMember/{id}")
        public String getMemberDetail(@PathVariable("storeCode") String storeCode, @PathVariable("id") String id, Model model) {
            Members member = memberService.getMemberById(id);
            model.addAttribute("member", member);
            model.addAttribute("storeCode", storeCode);
            return "admin/chat/detailMember";
        }

    }



