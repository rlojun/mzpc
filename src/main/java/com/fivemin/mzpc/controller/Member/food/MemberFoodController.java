package com.fivemin.mzpc.controller.Member.food;

import com.fivemin.mzpc.data.dto.FavoritesDto;
import com.fivemin.mzpc.data.dto.FoodDto;
import com.fivemin.mzpc.data.entity.Cart;
import com.fivemin.mzpc.data.entity.Favorites;
import com.fivemin.mzpc.data.entity.Members;
import com.fivemin.mzpc.service.member.CartService;
import com.fivemin.mzpc.service.member.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
    private final CartService cartService;

    public MemberFoodController(FoodService foodService, CartService cartService) {
        this.foodService = foodService;
        this.cartService = cartService;
    }

    @GetMapping("/listFood")
    public ModelAndView listFoodMember(Model model, @PathVariable(required = false) String storeName, HttpSession httpSession) {
        String validStoreName = (String) httpSession.getAttribute("storeName");
        Members members = (Members) httpSession.getAttribute("members");
        model.addAttribute("validStoreName", validStoreName);

        if (validStoreName != null) {
            List<FoodDto> foodDtoList = foodService.getFoodList(storeName);
            // 토핑 아닌 음식들만 노출 위한 메서드
            List<FoodDto> filteredFoodList = foodService.filterFoodByToppings(foodDtoList);
            List<String> distinctFoodCategories = foodService.createDistinctCategories(filteredFoodList);
            List<FavoritesDto> favorites = foodService.getFavorites(members.getIdx());

            model.addAttribute("foodDtoList", filteredFoodList);
            model.addAttribute("distinctFoodCategories", distinctFoodCategories);
            model.addAttribute("favorites", favorites);

            List<Cart> cartList = cartService.getCartListByMemberIdx(members.getIdx());

            if (cartList != null) {
                List<Cart> filteredCartList = cartList.stream()
                        .filter(cart -> !cart.isOrderComplete())
                        .collect(Collectors.toList());
                    model.addAttribute("cartList", filteredCartList);
            }
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

        log.info("MFController - detail: foodName: {} + foodCode: {}" , foodName, foodCode);

        if (validStoreName != null) {
            String encodedStoreName = URLEncoder.encode(validStoreName, StandardCharsets.UTF_8);
            model.addAttribute("encodedStoreName", encodedStoreName);

            FoodDto foodDetails = foodService.getFoodDetails(foodCode);
            model.addAttribute("foodDetails", foodDetails);

            List<FoodDto> toppings = foodService.getToppingsByCategory(foodDetails.getCategoryName());
            model.addAttribute("toppings", toppings);
        }

        return new ModelAndView("members/food/detailFood");
    }


//    @GetMapping("/favorites")
//    public String listFoodFavorites() {
//       return "members/food/listFood/{favorites}";
//    }


//    addFoodFavorites
//    (음식 즐겨 찾기 설정 하기)
    @PostMapping("/addFavorite")
    public ResponseEntity<String> addFavorite(@RequestParam Long foodIdx,
                                              @PathVariable(required = false) String storeName,
                                              HttpSession httpSession) {
        Members members = (Members) httpSession.getAttribute("members");

        if (!foodService.isFoodInFavorites(foodIdx, members)) {
            foodService.addFavorite(foodIdx, members);
            return ResponseEntity.ok("즐겨 찾기에 추가했습니다.");
        } else {
            return ResponseEntity.badRequest().body("이 음식은 이미 즐겨 찾기에 추가되었습니다.");
        }
    }

    @Transactional
    @DeleteMapping("/removeFavorite")
    public ResponseEntity<String> removeFavorite(@RequestParam Long favoriteIdx,
                                                 @PathVariable(required = false) String storeName) {
        foodService.removeFavorite(favoriteIdx);
        return ResponseEntity.ok("즐겨 찾기에 제거했습니다.");
    }

}