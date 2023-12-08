package com.fivemin.mzpc.controller.Member.food;

/*
- 기능
자신의 주문 목록 리스트
    카트에서 결제함에 따른 리스트 목록 추가
 */

import com.fivemin.mzpc.data.dto.OrdersDto;
import com.fivemin.mzpc.data.entity.Cart;
import com.fivemin.mzpc.data.entity.Members;
import com.fivemin.mzpc.data.entity.Orders;
import com.fivemin.mzpc.service.member.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/members/{storeName}/order")
public class MemberOrderController {

    private final OrderService orderService;

    public MemberOrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/list")
    public ModelAndView listOrderMember(Model model, @PathVariable(required = false) String storeName, HttpSession httpSession) {

        Members members = (Members) httpSession.getAttribute("members");
        List<Orders> ordersLog = orderService.getOrdersByMemberIdx(members.getIdx());

        for (Orders order : ordersLog) {
            log.info("Order ID: {}", order.getIdx()); // Adjust the property based on your Orders entity
            for (Cart carts : order.getCarts()) {
                log.info("Cart ID: {}", carts.getIdx()); // Adjust the property based on your Cart entity
                log.info("Food Name: {}", carts.getFood().getName()); // Adjust the property based on your Food entity
                log.info("Food Price: {}원", carts.getFood().getPrice()); // Adjust the property based on your Food entity
            }
        }

        if (ordersLog != null) {
            model.addAttribute("orders", ordersLog);
        }

        return new ModelAndView("members/food/listOrder");
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@ModelAttribute OrdersDto cartList,
                                           @PathVariable(required = false) String storeName,
                                           HttpSession httpSession) {
        String encodedStoreName = URLEncoder.encode(storeName, StandardCharsets.UTF_8);

        Members member = (Members) httpSession.getAttribute("members");

        Orders ordersDto = orderService.checkoutOrder(member);
        httpSession.setAttribute("ordersDto", ordersDto);
        httpSession.getAttribute("ordersDto");

        String redirectUrl = "/members/" + encodedStoreName + "/order/list";
        return ResponseEntity.ok(redirectUrl);
    }

}
