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
@RequestMapping("/admin/{storeCode}")
public class AdminOrderController {

    private AdminOrderService adminOrderService;

    @Autowired
    public AdminOrderController(AdminOrderService adminOrderService) {
        this.adminOrderService = adminOrderService;
    }

    // 사용자가 주문한 주문 목록
    @GetMapping("/orderList")
    public String orderList(@PathVariable String storeCode, Model model){

        List<OrdersDto> filteredOrdersList = adminOrderService.getOrderList(storeCode);

        model.addAttribute("ordersDtos", filteredOrdersList);
        model.addAttribute("storeCode",storeCode);
        return "admin/order/orderList";
    }

}
