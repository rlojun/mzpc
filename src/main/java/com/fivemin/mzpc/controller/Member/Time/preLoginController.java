package com.fivemin.mzpc.controller.Member.Time;

import com.fivemin.mzpc.data.dto.TimeDto;
import com.fivemin.mzpc.data.entity.Members;
import com.fivemin.mzpc.data.entity.Times;
import com.fivemin.mzpc.service.KakaoPayService;
import com.fivemin.mzpc.service.LoginService;
import com.fivemin.mzpc.service.SessionService;
import com.fivemin.mzpc.service.member.MemberTimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/pre/{storeName}")
public class preLoginController {

    @Autowired
    MemberTimeService memberTimeService;

    @Autowired
    LoginService loginService;

    @Autowired
    KakaoPayService kakaopay;

    @Autowired
    SessionService sessionService;

    @GetMapping("/time")
    public String listTime(@PathVariable String storeName, Model model, HttpSession session){
        List<TimeDto> memberListTime = memberTimeService.listTime(storeName);
        String memberId = (String) session.getAttribute("id");
        String memberCode = loginService.findByMemberId(memberId).getCode();

        model.addAttribute("memberCode", memberCode);
        model.addAttribute("memberListTime", memberListTime);
        return "members/time/preLogin/listTime";
    }

    // 시간 구매 페이지
    @GetMapping("/purchaseTime/{memberCode}/{timeCode}")
    public String purchaseTime(@PathVariable String storeName,
                               @PathVariable String timeCode,
                               @PathVariable String memberCode,
                               @RequestParam(value = "usedMileage", required = false, defaultValue = "0") int usedMileage,
                               Model model,
                               HttpServletRequest request) {
        HttpSession session = request.getSession();
        String memberId = (String) session.getAttribute("id");

        // 로그인한 사용자의 마일리지
        Members loginUser = loginService.findByMemberId(memberId);
        int memberMileage = loginUser.getMileage();

        Times times = memberTimeService.detailTime(timeCode);

        model.addAttribute("times", times);
        model.addAttribute("storeName", storeName);
        model.addAttribute("timeCode", timeCode);
        model.addAttribute("memberMileage", memberMileage);
        model.addAttribute("usedMileage", usedMileage);
        model.addAttribute("memberCode", memberCode);
        return "members/time/preLogin/orderTime";
    }

    @PostMapping("/purchaseTime/{memberCode}/{timeCode}")
    public String kakaoPay(@PathVariable String timeCode, HttpSession session,
                           @RequestParam String memberId,
                           @RequestParam int usedMileage,
                           @RequestParam int timePrice,
                           @PathVariable String memberCode,
                           @RequestParam @DateTimeFormat(pattern = "H:mm:ss") LocalTime additionalTime) {

        sessionService.saveStringSession(memberCode, "memberId",memberId);
        sessionService.saveIntSession(memberCode,"usedMileage", usedMileage);
        sessionService.saveIntSession(memberCode, "timePrice", timePrice);
        sessionService.saveLocalTimeSession(memberCode, "additionalTime", additionalTime);
        log.info("kakaoPay post............................................");
        return "redirect:" + kakaopay.kakaoPayReady(timeCode, usedMileage, memberCode);
    }
//
//
//
//
//
//    // 시간 구매 기능
//    @PostMapping("/purchaseTime/{timeCode}")
//    public String purchaseTime(@PathVariable String timeCode,
//                               Model model,
//                               @RequestParam String memberId,
//                               @RequestParam int usedMileage,
//                               @RequestParam int timePrice,
//                               @RequestParam @DateTimeFormat(pattern = "H:mm:ss") LocalTime additionalTime) {
//        memberTimeService.purchaseTime(memberId, usedMileage, timePrice, additionalTime, timeCode);
//        model.addAttribute("timeCode", timeCode);
//        return "redirect:/login";
//    }
}
