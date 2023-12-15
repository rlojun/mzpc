package com.fivemin.mzpc.controller.admin;


import com.fivemin.mzpc.data.dto.OrdersDto;
import com.fivemin.mzpc.service.admin.AdminOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin/{storeCode}")
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    @Autowired
    public AdminOrderController(AdminOrderService adminOrderService) {
        this.adminOrderService = adminOrderService;
    }

    // 사용자가 주문한 주문 목록
    @GetMapping("/orderList")
    public String orderList(@PathVariable String storeCode, Model model){
        List<OrdersDto> filteredOrdersList = adminOrderService.getOrderList(storeCode);
        boolean orderStatus =adminOrderService.checkOrder();

        model.addAttribute("orderStatus", orderStatus);
        model.addAttribute("ordersDtos", filteredOrdersList);
        model.addAttribute("storeCode",storeCode);
        return "admin/order/orderList";
    }

}
