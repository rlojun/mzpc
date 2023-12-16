package com.fivemin.mzpc.controller.chat;

import com.fivemin.mzpc.data.entity.Inquiry;
import com.fivemin.mzpc.data.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//문의 제출 및 관리자 페이지에 문의 내용을 표시하는 기능을 담당하는 컨트롤러
@Controller
public class InquiryController {

    @Autowired
    private InquiryRepository inquiryRepository;

    @PostMapping("/submit-query")
    public String submitQuery(@RequestParam String title, @RequestParam String content, @RequestParam String memberId) {
        Inquiry inquiry = new Inquiry();
        inquiry.setTitle(title);
        inquiry.setContent(content);
        inquiry.setMemberId(memberId);
        inquiryRepository.save(inquiry);
        return "redirect:/";
    }

    @GetMapping("/admin")
    public String showInquiries(Model model) {
        model.addAttribute("inquiries", inquiryRepository.findAll());
        return "admin";
    }
}

