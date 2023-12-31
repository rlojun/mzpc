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
    public ResponseEntity<String> addToCart(@RequestParam(name = "code") String mainFood,
                                            @RequestParam(name = "toppings", required = false) List<String> selectedToppings,
                                            @PathVariable(required = false) String storeName,
                                            HttpSession httpSession) {

        String encodedStoreName = URLEncoder.encode(storeName, StandardCharsets.UTF_8);
        Members members = (Members) httpSession.getAttribute("members");
        List<Cart> cartItems = cartService.addToCart(mainFood, selectedToppings, members);
        httpSession.setAttribute("cartItems", cartItems);
        httpSession.getAttribute("cartItems");

        String redirectUrl = "/members/" + encodedStoreName + "/food/listFood";
        return ResponseEntity.ok(redirectUrl);

    }

    @DeleteMapping("/removeFromCart")
    public ResponseEntity<String> removeFromCart(@RequestParam("cartItemIdx") Long cartItemIdx,
                                                 @PathVariable(required = false) String storeName) {
        cartService.removeFromCart(cartItemIdx);
        return ResponseEntity.ok("카트 아이템 제거 완료");
    }
}
