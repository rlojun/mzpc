package com.fivemin.mzpc.controller.Member.Time;

import com.fivemin.mzpc.data.dto.TimeDto;
import com.fivemin.mzpc.data.entity.Members;
import com.fivemin.mzpc.data.entity.Times;
import com.fivemin.mzpc.service.LoginService;
import com.fivemin.mzpc.service.members.MemberTimeService;
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

    @GetMapping("/time")
    public String listTime(@PathVariable String storeName, Model model){
        List<TimeDto> memberListTime = memberTimeService.listTime(storeName);
        model.addAttribute("memberListTime", memberListTime);
        return "members/time/preLogin/listTime";
    }

    // 시간 구매 페이지
    @GetMapping("/purchaseTime/{timeCode}")
    public String purchaseTime(@PathVariable String storeName,
                               @PathVariable String timeCode,
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
        return "members/time/preLogin/orderTime";
    }

    // 시간 구매 기능
    @PostMapping("/purchaseTime/{timeCode}")
    public String purchaseTime(@PathVariable String timeCode,
                               Model model,
                               @RequestParam String memberId,
                               @RequestParam int usedMileage,
                               @RequestParam int timePrice,
                               @RequestParam @DateTimeFormat(pattern = "H:mm:ss") LocalTime additionalTime) {
        memberTimeService.purchaseTime(memberId, usedMileage, timePrice, additionalTime, timeCode);
        model.addAttribute("timeCode", timeCode);
        return "redirect:/login";
    }
}
