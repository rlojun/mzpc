package com.fivemin.mzpc.controller.Member.food;

import com.fivemin.mzpc.data.entity.Cart;
import com.fivemin.mzpc.data.entity.Food;
import com.fivemin.mzpc.data.entity.Members;
import com.fivemin.mzpc.service.member.CartService;
import com.fivemin.mzpc.service.member.FoodService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
- 기능
음식, 토핑 +-n  기능
결제방식 선택 및 요청사항 기능
결제 api
 */
@Data
@Slf4j
@RestController
@RequestMapping("/members/{storeName}/cart")
public class CartController {

    private final CartService cartService;
    private final FoodService foodService;

    public CartController(CartService cartService, FoodService foodService) {
        this.cartService = cartService;
        this.foodService = foodService;
    }

    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(@RequestParam("code") String mainFood,
                                            @RequestParam("toppings") String selectedToppings,
                                            @PathVariable(required = false) String storeName,
                                            HttpSession httpSession) {

        String encodedStoreName = URLEncoder.encode(storeName, StandardCharsets.UTF_8);
        Members members = (Members) httpSession.getAttribute("members");
        log.info("selected Toppings : {}", selectedToppings);

        List<Cart> cartItems = cartService.addToCart(mainFood, selectedToppings, members);
        httpSession.setAttribute("cartItems", cartItems);

        httpSession.getAttribute("cartItems");

        String redirectUrl = "/members/" + encodedStoreName + "/food/listFood";
        return ResponseEntity.ok(redirectUrl);

    }

    /*

    addCart
    (카트에 음식 + 토핑 추가하기)

    purchaseCartKakao
    (카트 상품 카카오페이로 결제하기 + 요청사항)

    purchaseCartCash
    (카트 상품 현금으로 결제하기 + 요청사항)

    purchaseCartCard
    (카트 상품 카드로 결제하기 + 요청사항)

    deleteCart
    (카트에 담긴 상품 제거)

     */
}
