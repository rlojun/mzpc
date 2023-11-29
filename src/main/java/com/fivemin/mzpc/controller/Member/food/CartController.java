package com.fivemin.mzpc.controller.Member.food;

import com.fivemin.mzpc.data.dto.CartFoodDto;
import com.fivemin.mzpc.data.dto.FoodDto;
import com.fivemin.mzpc.service.member.CartService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/*
- 기능
음식, 토핑 +-n  기능
결제방식 선택 및 요청사항 기능
결제 api
 */
@Data
@Slf4j
@RequestMapping("/members/{storeName}/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PostMapping("/addToCart")
    public String addToCart(@ModelAttribute FoodDto foodDetails,
                            @PathVariable(required = false) String storeName,
                            HttpSession httpSession) {
        String encodedStoreName = URLEncoder.encode(storeName, StandardCharsets.UTF_8);

        List<CartFoodDto> cartItems = (List<CartFoodDto>) httpSession.getAttribute("cartItems");

        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        log.info("MFController - Cart: foodDetails {}", foodDetails);

        cartItems = cartService.addToCart(cartItems, foodDetails.getName(), foodDetails.getCode(), foodDetails.getPrice());
        httpSession.setAttribute("cartItems", cartItems);
        log.info("MFController - cart: cartItems {} :", cartItems);

        return "redirect:/members/" + encodedStoreName + "/food/listFood";
    }
    @GetMapping("/showCart")
    public String showCart(@PathVariable String storeName, Model model, HttpSession httpSession) {

        String memberCode = (String) httpSession.getAttribute("memberCode");
        List<CartFoodDto> cartItems = cartService.getCartByMemberCode(memberCode);
        model.addAttribute("cartItems", cartItems);
        return "cart";  // Assuming "cart" is the Thymeleaf template for the cart page
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
