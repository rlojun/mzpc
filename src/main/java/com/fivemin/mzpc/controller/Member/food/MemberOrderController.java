package com.fivemin.mzpc.controller.Member.food;

/*
- 기능
자신의 주문 목록 리스트
    카트에서 결제함에 따른 리스트 목록 추가
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{memberId}/order")
public class MemberOrderController {

    // 자신의 주문목록
    @GetMapping("/listOrder")
    public String listOrder(){

        return "members/food/listOrder";
    }
}
