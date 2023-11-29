package com.fivemin.mzpc.controller.Member.food;

import com.fivemin.mzpc.data.dto.FoodDto;
import com.fivemin.mzpc.data.dto.MenuDto;
import com.fivemin.mzpc.service.member.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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

    public MemberFoodController(FoodService foodService) {
        this.foodService = foodService;
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

        log.info("MFController - detail: foodName: {} + foodCode: {}" , foodName, foodCode);

        if (validStoreName != null) {
            String encodedStoreName = URLEncoder.encode(validStoreName, StandardCharsets.UTF_8);
            model.addAttribute("encodedStoreName", encodedStoreName);

            FoodDto foodDetails = foodService.getFoodDetails(foodCode);
            model.addAttribute("foodDetails", foodDetails);
            log.info("MFController - detail: FoodDetails {}", foodDetails);
        }

        return new ModelAndView("members/food/detailFood");
    }


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
//**************************************** 삭제 예정
//    returnFoodList
//    (음식 상세 화면 에서 음식 상품 목록 으로 돌아 가기 메서드)
//
//     */

}