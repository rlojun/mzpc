package com.fivemin.mzpc.controller.admin;

import com.fivemin.mzpc.data.dto.OrdersDto;
import com.fivemin.mzpc.service.admin.AdminOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class AdminOrderRestController {

    private AdminOrderService adminOrderService;

    @Autowired
    public AdminOrderRestController(AdminOrderService adminOrderService) {
        this.adminOrderService = adminOrderService;
    }

    @PostMapping(value = "/completeOrder")
    public ResponseEntity<String> completeOrder(@RequestBody OrdersDto ordersDto) {

        adminOrderService.completeOrder(ordersDto);

        return ResponseEntity.ok("주문 완료 되었습니다.");
    }
}
