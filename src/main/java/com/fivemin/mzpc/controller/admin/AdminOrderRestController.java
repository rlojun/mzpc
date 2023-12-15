package com.fivemin.mzpc.controller.admin;

import com.fivemin.mzpc.data.dto.OrdersDto;
import com.fivemin.mzpc.service.admin.AdminOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/orders")
public class AdminOrderRestController {

    private final AdminOrderService adminOrderService;

    @Autowired
    public AdminOrderRestController(AdminOrderService adminOrderService) {
        this.adminOrderService = adminOrderService;
    }

    @PostMapping(value = "/completeOrder")
    public ResponseEntity<String> completeOrder(@RequestBody OrdersDto ordersDto) {

        adminOrderService.completeOrder(ordersDto);

        return ResponseEntity.ok("주문 완료 되었습니다.");
    }

    @DeleteMapping(value = "/rejectOrder")
    public ResponseEntity<String> rejectOrder(@RequestBody String orderCode) {

        adminOrderService.rejectOrder(orderCode);

        return ResponseEntity.ok("취소되었습니다.");
    }

}
