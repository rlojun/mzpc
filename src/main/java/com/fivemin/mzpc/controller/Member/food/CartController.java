package com.fivemin.mzpc.controller.Member.food;

import com.fivemin.mzpc.data.dto.CartDto;
import com.fivemin.mzpc.data.dto.FoodDto;
import com.fivemin.mzpc.data.entity.Food;
import com.fivemin.mzpc.service.member.CartService;
import com.fivemin.mzpc.service.member.FoodService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
    public ResponseEntity<String> addToCart(@ModelAttribute FoodDto mainFood,
                                            @RequestParam("code") String foodCode,
                                            @RequestParam("toppings") String selectedToppings,
                                            @PathVariable(required = false) String storeName,
                                            HttpSession httpSession) {

        String encodedStoreName = URLEncoder.encode(storeName, StandardCharsets.UTF_8);
        CartDto cartItems= (CartDto) httpSession.getAttribute("cartItems");
        log.info("CartController: mainFood {}", mainFood);

        cartItems = cartService.addToCart(cartItems, httpSession, foodCode, selectedToppings);

        httpSession.setAttribute("cartItems", cartItems);
        log.info("CartController: number of cartItems {} :", cartItems.getFood().size());
        log.info("CartController: cartItems {} :", cartItems.getFood().stream()
                .map(Food::getName)
                .collect(Collectors.toList()));

        String redirectUrl = "/members/" + encodedStoreName + "/food/listFood";
        return ResponseEntity.ok(redirectUrl);

    }

//    @PostMapping("/addToCart")
//    public ResponseEntity<String> addToCart(@ModelAttribute FoodDto foodDetails,
//                                            @RequestParam("code") String foodCode,
//                                            @PathVariable(required = false) String storeName,
//                                            HttpSession httpSession) {
//
//        String encodedStoreName = URLEncoder.encode(storeName, StandardCharsets.UTF_8);
//        List<CartDto> cartItems = (List<CartDto>) httpSession.getAttribute("cartItems");
//        CartDto cartItem = (CartDto) httpSession.getAttribute("cartItems");
//
//
////        if (cartItems == null) {
////            cartItems = new ArrayList<>();
////        }
//
//        log.info("CartController: foodDetails {}", foodDetails);
//        if (foodDetails.isTopping() == null) {
//            foodDetails.setTopping(false);
//        }
//        Food food = foodService.convertDtoToEntity(foodDetails);
//        log.info("food : {}", food);
//
////        cartItems = cartService.addToCart(cartItems, httpSession, foodCode);
//        cartItem = cartService.addToCart(cartItem, httpSession, foodCode);
//        httpSession.setAttribute("cartItem", cartItem);
//        log.info("CartController: cartItem {} :", cartItems);
////        httpSession.setAttribute("cartItems", cartItems);
////        log.info("CartController: cartItems {} :", cartItems);
//
//        String redirectUrl = "/members/" + encodedStoreName + "/food/listFood";
//        log.info(redirectUrl);
//        return ResponseEntity.ok(redirectUrl);
//
//    }

//    @GetMapping("/showCart")
//    public ResponseEntity<List<CartDto>> showCart(@PathVariable String storeName, HttpSession httpSession) {
//
//        Long memberIdx = (Long) httpSession.getAttribute("memberIdx");
//        List<CartDto> cartItems = cartService.getCartByMemberIdx(memberIdx);
//
//        return ResponseEntity.ok(cartItems);
//    }

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
