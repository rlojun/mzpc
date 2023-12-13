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
import com.fivemin.mzpc.service.KakaoPayService;
import com.fivemin.mzpc.service.LoginService;
import com.fivemin.mzpc.service.members.MemberTimeService;
import lombok.Setter;
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

    @Setter(onMethod_ = @Autowired)
    private KakaoPayService kakaopay;

    // 시간 상품 목록
    @GetMapping("/time")
    public String listTime(@PathVariable String storeName, Model model) {
        log.info("storeName : {} : ==>", storeName);
        List<TimeDto> memberListTime = memberTimeService.listTime(storeName);
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

    // 카카오 페이 결제 준비
    @PostMapping("/purchaseTime/{timeCode}")
    public String kakaoPay(@PathVariable String timeCode, HttpSession session,
                           @RequestParam String memberId,
                           @RequestParam int usedMileage,
                           @RequestParam int timePrice,
                           @RequestParam @DateTimeFormat(pattern = "H:mm:ss") LocalTime additionalTime) {
        session.setAttribute("memberId", memberId);
        session.setAttribute("usedMileage", usedMileage);
        session.setAttribute("timePrice", timePrice);
        session.setAttribute("additionalTime", additionalTime);

        log.info("Session ID in kakaoPay: " + session.getId());
        log.info("usedMileage in kakaoPay: " + session.getAttribute("usedMileage"));

        log.info("kakaoPay post............................................");
        return "redirect:" + kakaopay.kakaoPayReady(timeCode, usedMileage);
    }

    // 카카오페이 결제 성공 페이지
    @GetMapping("/purchaseTime/{timeCode}/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model,
                                  @PathVariable String timeCode, @PathVariable String storeName,
                                  HttpSession session) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

        Integer usedMileage = (Integer) session.getAttribute("usedMileage");

        if (usedMileage == null) {
            usedMileage =0;
        }
        log.info("usedMileage : {}", usedMileage);
        model.addAttribute("storeName", storeName);
        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token, timeCode, usedMileage));
        return "kakao/kakaoPaySuccess";
    }

    // 시간 추가 및 마일리지 적립 로직
    @PostMapping("/purchaseTime/{timeCode}/kakaoPaySuccess")
    public String kakaoPaySuccess(@PathVariable String storeName, @PathVariable String timeCode,
                                  Model model,HttpSession session) {
        String memberId = (String) session.getAttribute("memberId");
        int usedMileage = (int) session.getAttribute("usedMileage");
        int timePrice = (int) session.getAttribute("timePrice");
        LocalTime additionalTime = (LocalTime) session.getAttribute("additionalTime");

        memberTimeService.purchaseTime(memberId, usedMileage, timePrice, additionalTime, timeCode);

        model.addAttribute("timeCode", timeCode);
        String encodedStoreName = URLEncoder.encode(storeName, StandardCharsets.UTF_8);
        model.addAttribute("storeName", encodedStoreName);
        return String.format("redirect:/members/%s", encodedStoreName);
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
