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
import com.fivemin.mzpc.service.SessionService;
import com.fivemin.mzpc.service.member.MemberTimeService;
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

    @Autowired
    SessionService sessionService;

    // 시간 상품 목록
    @GetMapping("/time")
    public String listTime(@PathVariable String storeName, Model model, HttpSession session) {
        log.info("storeName : {} : ==>", storeName);
        List<TimeDto> memberListTime = memberTimeService.listTime(storeName);
        String memberId = (String) session.getAttribute("id");
        log.info("memberId : {}", memberId);
        String memberCode = loginService.findByMemberId(memberId).getCode();

        model.addAttribute("memberCode", memberCode);
        model.addAttribute("memberListTime", memberListTime);
        return "members/time/listTime";
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
        model.addAttribute("memberCode", memberCode);
        return "members/time/orderTime";
    }

    // 카카오 페이 결제 준비
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

        log.info("usedMileage in kakaoPay: " + usedMileage);
        log.info("memberId : {} ", memberId);

        log.info("kakaoPay post............................................");
        return "redirect:" + kakaopay.kakaoPayReady(timeCode, usedMileage, memberCode);
    }

    // 카카오페이 결제 성공 페이지
    // 여기서 다시 세션 담아야함
    @GetMapping("/purchaseTime/{memberCode}/{timeCode}/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model,
                                  @PathVariable String timeCode, @PathVariable String storeName,
                                  HttpSession session, @PathVariable String memberCode) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

        String memberId = sessionService.findByCodeAndName(memberCode, "memberId").getStringValue();
        session.setAttribute("id", memberId);
        log.info("memberId : {}", memberId);
        Members members = loginService.findByMemberId(memberId);
        session.setAttribute("members", members);

        int usedMileage = sessionService.findByCodeAndName(memberCode, "usedMileage").getIntValue();

        log.info("usedMileage : {}", usedMileage);
        model.addAttribute("storeName", storeName);
        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token, timeCode, usedMileage));
        return "kakao/KakaoPaySuccess";
    }

    // 시간 추가 및 마일리지 적립 로직
    @PostMapping("/purchaseTime/{memberCode}/{timeCode}/kakaoPaySuccess")
    public String kakaoPaySuccess(@PathVariable String storeName, @PathVariable String timeCode,
                                  Model model,HttpSession session, @PathVariable String memberCode) {
        String memberId = (String) session.getAttribute("id");
        int usedMileage = sessionService.findByCodeAndName(memberCode, "usedMileage").getIntValue();
        int timePrice = sessionService.findByCodeAndName(memberCode, "timePrice").getIntValue();
        LocalTime additionalTime = sessionService.findByCodeAndName(memberCode, "additionalTime").getLocalTimeValue();

        memberTimeService.purchaseTime(memberId, usedMileage, timePrice, additionalTime, timeCode);

        sessionService.deleteByCode(memberCode);

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
