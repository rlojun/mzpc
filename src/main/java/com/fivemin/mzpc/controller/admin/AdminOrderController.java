package com.fivemin.mzpc.controller.admin;


import com.fivemin.mzpc.data.dto.OrdersDto;
import com.fivemin.mzpc.service.admin.AdminOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
-기능
사용자가 주문한 주문 리스트
완료, 취소 버튼 생성
    음식 나가고 나서 완료버튼, 조리 불가시 취소 버튼
    두 버튼다 클릭시 목록에서 사라짐.
 */

@Controller
@Slf4j
@RequestMapping("/admin/{stoerCode}")
public class AdminOrderController {

    private AdminOrderService adminOrderService;

    @Autowired
    public AdminOrderController(AdminOrderService adminOrderService) {
        this.adminOrderService = adminOrderService;
    }

    // 사용자가 주문한 주문 목록
    @GetMapping("/orderList")
    public String orderList(@PathVariable String stoerCode, Model model){

        List<OrdersDto> ordersDtos = adminOrderService.getOrderList(stoerCode);

        model.addAttribute("ordersDtos",ordersDtos);
        model.addAttribute("storeCode",stoerCode);
        log.info("ordersDtos : {}",ordersDtos);
        return "/admin/order/orderList";
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
//    @GetMapping("/cancelOrder")
//    public String cancelOrder() {
//
//        return "redirect:/{adminId}/listOrder";
//    }

    // 주문 목록의 주문 완료 버튼 기능
//    @GetMapping("/completeOrder")
//    public String completeOrder() {
//
//        return "redirect:/{adminId}/listOrder";
//    }
}
