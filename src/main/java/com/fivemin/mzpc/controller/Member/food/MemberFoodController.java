package com.fivemin.mzpc.controller.Member.food;

import com.fivemin.mzpc.data.dto.CartFoodDto;
import com.fivemin.mzpc.data.dto.FoodDto;
import com.fivemin.mzpc.data.dto.MenuDto;
import com.fivemin.mzpc.service.member.CartService;
import com.fivemin.mzpc.service.member.CategoryService;
import com.fivemin.mzpc.service.member.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/*
-기능
카테고리 별 음식 상품 리스트
즐겨찾기 기능
    즐겨찾기 목록
음식 상세보기
    음식 별 토핑 목록 기능, 토핑 선택
 */
@Controller
@Slf4j
@RequestMapping("/members/{storeName}/food")
public class MemberFoodController {

    private final FoodService foodService;
    private final CategoryService categoryService;
    private final CartService cartService;

    public MemberFoodController(FoodService foodService, CategoryService categoryService, CartService cartService) {
        this.foodService = foodService;
        this.categoryService = categoryService;
        this.cartService = cartService;
    }

    @GetMapping("/listFood")
    public ModelAndView listFoodMember(Model model, @PathVariable(required = false) String storeName, HttpSession httpSession) {
        String validStoreName = (String) httpSession.getAttribute("storeName");
        model.addAttribute("validStoreName", validStoreName);

        if (validStoreName != null) {
            List<MenuDto> menuDtoList = foodService.getListMenu(storeName);
            model.addAttribute("menuDtoList", menuDtoList);
            // log.info("MFController - list: Menu {}", menuDtoList);

            // test
            List<String> menuDtoCategories = menuDtoList.stream()
                    .map(MenuDto::getCategoryName)
                    .distinct()
                    .collect(Collectors.toList());

            model.addAttribute("menuDtoCategories", menuDtoCategories);
        }

        return new ModelAndView("members/food/listFood");
    }

    @GetMapping("/detail/{foodName}")
    public ModelAndView detailFoodMember(Model model,
                                         @PathVariable(required = false) String storeName,
                                         @PathVariable String foodName,
                                         @RequestParam String foodCode,
                                         HttpSession httpSession) {
        String validStoreName = (String) httpSession.getAttribute("storeName");
        model.addAttribute("foodName", foodName);
        model.addAttribute("foodCode", foodCode);

        log.info("MFController - detail: foodName: {}", foodName);
        log.info("MFController - detail: foodCode: {}", foodCode);

        if (validStoreName != null) {
            String encodedStoreName = URLEncoder.encode(validStoreName, StandardCharsets.UTF_8);
            model.addAttribute("encodedStoreName", encodedStoreName);

            FoodDto foodDetails = foodService.getFoodDetails(foodCode);
            model.addAttribute("foodDetails", foodDetails);
            log.info("MFController - detail: FoodDetails {}", foodDetails);
        }

        return new ModelAndView("members/food/detailFood");
    }
    @PostMapping(value = "/addToCart", consumes = "application/json")
    public String addToCart(@RequestBody CartFoodDto cartItems,
                            @PathVariable(required = false) String storeName,
                            @RequestHeader HttpHeaders headers,
                            HttpSession httpSession) {
        String encodedStoreName = URLEncoder.encode(storeName, StandardCharsets.UTF_8);

        System.out.println("Headers: " + headers);
        List<CartFoodDto> existingCartItems = (List<CartFoodDto>) httpSession.getAttribute("cartItems");

        if (existingCartItems == null) {
            existingCartItems = new ArrayList<>();
        }

        existingCartItems.add(cartItems);
        log.info("cartItems {}", cartItems);

        httpSession.setAttribute("cartItems", existingCartItems);

        return "redirect:/members/" + encodedStoreName + "/food/listFood";


    }


//    @PostMapping("/addToCart")
//    public String addToCart(@ModelAttribute FoodDto foodDetails,
//                            @PathVariable(required = false) String storeName,
//                            @RequestBody List<CartFoodDto> cartItems,
//                            HttpSession httpSession) {
////        String validStoreName = (String) httpSession.getAttribute("storeName");
////        String encodedStoreName = URLEncoder.encode(validStoreName, StandardCharsets.UTF_8);
//        String encodedStoreName = URLEncoder.encode(storeName, StandardCharsets.UTF_8);
//
//        // List<CartFoodDto> cartItems = (List<CartFoodDto>) httpSession.getAttribute("cartItems");
//        httpSession.getAttribute("cartItems");
//
//        if (cartItems == null) {
//            cartItems = new ArrayList<>();
//        }
//        log.info("MFController - Cart: foodDetails {}", foodDetails);
//        cartItems = cartService.addToCart(cartItems, foodDetails.getName(), foodDetails.getCode(), foodDetails.getPrice());
//
//        log.info("food name, code, price: {}, {}, {}", foodDetails.getName(), foodDetails.getCode(), foodDetails.getPrice());
//        httpSession.setAttribute("cartItems", cartItems);
//        log.info("MFController - cart: cartItems {} :", cartItems);
//
//        return "redirect:/members/" + encodedStoreName + "/food/listFood";
//
//
//    }


//    @GetMapping("/{category}")
//    public String listFoodCategory(@PathVariable (required = false) String storeName, @PathVariable String category, Model model) {
//        List<Food> foodList = foodService.getFoodByCategory(category);
//        List<Category> foodCategories = categoryService.getAllCategories();
//        model.addAttribute("foodList", foodList);
//        model.addAttribute("foodCategories", foodCategories);
//
//        return "members/food/listFood";
//    }
//
//    // @GetMapping("/favorites")
//    // public String listFoodFavorites() {
//    //    return "members/food/listFood/{favorites}";
//    // }
//
//
////    topping : true / false 로 구별 ( 토핑 / 일반 음식 )
////    @GetMapping("/{topping}")
////    public String listTopping() {
////        return "";
////    }
//
//    /*
//    addFoodFavorites
//    (음식 즐겨 찾기 설정 하기)
//
//    deleteFoodFavorites
//    (음식 즐겨 찾기 제거 하기)
//
//    detailFood
//    (음식 상품 선택하기)
//
//**************************************** 삭제 예정
//    returnFoodList
//    (음식 상세 화면 에서 음식 상품 목록 으로 돌아 가기 메서드)
//
//     */

}