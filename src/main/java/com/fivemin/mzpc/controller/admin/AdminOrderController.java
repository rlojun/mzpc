package com.fivemin.mzpc.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
-기능
사용자가 주문한 주문 리스트
완료, 취소 버튼 생성
    음식 나가고 나서 완료버튼, 조리 불가시 취소 버튼
    두 버튼다 클릭시 목록에서 사라짐.
 */

@Controller
@RequestMapping("/{adminId}/order")
public class AdminOrderController {

    // 사용자가 주문한 주문 목록
    @GetMapping("/listOrder")
    public String listOrder(){

        return "admin/listOrder";
    }

    // 취소, 완료 메서드 묶어서 하나 더 만들기 ( 매핑 어노테이션 url 작성)
    // 주문 완료 + 취소 url
//    @PostMapping("/{orderStatus}") //orderStatus 뷰에서 정한 id
//    public void orderStatus(@PathVariable String orderStatus){
//        if(orderStatus.equals("complete")){
//            completeOrder();
//        } else if (orderStatus.equals("complete")) {
//            cancelOrder();
//        }
//
//    }

    // 주문 목록의 주문 취소 버튼 기능
    @GetMapping("/cancelOrder")
    public String cancelOrder() {

        return "redirect:/{adminId}/listOrder";
    }

    // 주문 목록의 주문 완료 버튼 기능
    @GetMapping("/completeOrder")
    public String completeOrder() {

        return "redirect:/{adminId}/listOrder";
    }
}
