package com.fivemin.mzpc.controller.Member;

/*
- 기능
    시간 상품 목록
    시간 상품 선택 및 결제
        카카오 api
        결제시 마일리지 사용 가능, 사용시 마일리지 차감
    상품 구매시 유저의 남은시간 추가, 마일리지 추가
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/time")
public class MemberTimeController {

        // 시간 상품 목록
        @GetMapping("/listTime")
        public String listTime() {

            return "admin/time/listTime";
        }

//        // 시간 상품 선택 및 결제 페이지      rest 컨트롤러 이용
//        @GetMapping("/purchase/{timeId}")
//        public String purchaseTime(@PathVariable Long timeId, @RequestParam String memberId) {
//
//            return "/time/purchase";
//        }

        // 시간 상품 결제 처리
        @PostMapping("/pay/{timeId}")
        public String purchaseTime() {

//            addMileage(memberId);
//            useMileage(memberId);
            // 해당 로직 처리시 마일리지 사용, 적립이 이루어 진다.
            return "redirect:/time/listTime";
    }

    // 마일리지 적립 (add)
    public void addMileage(@PathVariable String memberId) {

        // return "redirect:/{memberId}/mileage/listMileage";
    }

    // 마일리지 사용(use)
    public void useMileage(@PathVariable String memberId) {

        // return "redirect:/{memberId}/mileage/listMileage";
    }

    // 마일리지 사용 및 적립 리스트
    @GetMapping("/listMileage")
    public String listMileage() {

        return "/members/time/listMileage";
    }
}
