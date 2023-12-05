package com.fivemin.mzpc.controller.Member.Time;

/*
- 기능
    시간 상품 목록
    시간 상품 선택 및 결제
        카카오 api
        결제시 마일리지 사용 가능, 사용시 마일리지 차감
    상품 구매시 유저의 남은시간 추가, 마일리지 추가
 */

import com.fivemin.mzpc.data.dto.TimeDto;
import com.fivemin.mzpc.data.entity.Members;
import com.fivemin.mzpc.data.entity.MileageInfo;
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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/members/{storeName}")
public class MemberTimeController {

    @Autowired
    MemberTimeService memberTimeService;

    @Autowired
    LoginService loginService;

    // 시간 상품 목록
    @GetMapping("/time")
    public String listTime(@PathVariable String storeName, Model model,
                           HttpServletRequest request) {
        log.info("storeName : {} : ==>", storeName);
        List<TimeDto> memberListTime = memberTimeService.listTime(storeName);

        // 남은 시간 띄우는 로직
        HttpSession session = request.getSession();
        String memberId = (String) session.getAttribute("id");
        Members updateTimeMember = memberTimeService.realRemainingTime(memberId);
        LocalTime realRemainingTime = updateTimeMember.getRemainingTime();
        log.info("realRemainingTime : {} : ", realRemainingTime);

        model.addAttribute("realRemainingTime", realRemainingTime);
        model.addAttribute("memberListTime", memberListTime);
        return "members/time/listTime";
    }

    // 시간 구매 페이지
    @GetMapping("/purchaseTime/{timeCode}")
    public String purchaseTime(@PathVariable String storeName,
                               @PathVariable String timeCode,
                               @RequestParam(value = "usedMileage", required = false, defaultValue = "0") int usedMileage,
                               Model model,
                               HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 로그인한 사용자 확인
        String memberId = (String) session.getAttribute("id");
        log.info("memberId: {} ", memberId);

        // 로그인한 사용자의 마일리지
        Members loginUser = loginService.findByMemberId(memberId);
        int memberMileage = loginUser.getMileage();

        Times times = memberTimeService.detailTime(timeCode);

        model.addAttribute("times", times);
        model.addAttribute("storeName", storeName);
        model.addAttribute("timeCode", timeCode);
        model.addAttribute("memberMileage", memberMileage);
        model.addAttribute("usedMileage", usedMileage);
        return "members/time/orderTime";
    }

    // 시간 구매 기능
    @PostMapping("/purchaseTime/{timeCode}")
    public String purchaseTime(@PathVariable String storeName,
                               @PathVariable String timeCode,
                               Model model,
                               @RequestParam String memberId,
                               @RequestParam int usedMileage,
                               @RequestParam int timePrice,
                               @RequestParam @DateTimeFormat(pattern = "H:mm:ss") LocalTime additionalTime) {
        memberTimeService.purchaseTime(memberId, usedMileage, timePrice, additionalTime, timeCode);
        model.addAttribute("timeCode", timeCode);
        String encodedStoreName = URLEncoder.encode(storeName, StandardCharsets.UTF_8);
        model.addAttribute("storeName", encodedStoreName);
        return String.format("redirect:/members/%s/time", encodedStoreName);
    }

    // 시간 구매 내역 및 마일리지 내역
    @GetMapping("/listPurchaseTime")
    public String listPurchaseTime(HttpServletRequest request, Model model,
                                   @PathVariable String storeName) {
        HttpSession session = request.getSession();
        String memberId = (String) session.getAttribute("id");
        List<MileageInfo> mileageInfos = memberTimeService.listMileageInfo(memberId);
        model.addAttribute("mileageInfos", mileageInfos);
        model.addAttribute("storeName", storeName);
        return "members/time/listPurchaseTime";
    }
}
