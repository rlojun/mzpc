package com.fivemin.mzpc.controller.Member.food;

import com.fivemin.mzpc.data.entity.Category;
import com.fivemin.mzpc.data.entity.Food;
// import com.fivemin.mzpc.service.CategoryService;
// import com.fivemin.mzpc.service.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
public class  MemberFoodController {

    @GetMapping
    public String memberListFood(){
        return "/members/food/listFood";
    }

//    private final FoodService foodService;
//    private final CategoryService categoryService;
//    public MemberFoodController(FoodService foodService, CategoryService categoryService) {
//        this.foodService = foodService;
//        this.categoryService = categoryService;
//
//    }
//
//    @GetMapping("/listFood")
//    // public String listFoodIndex(Model model, @PathVariable (required = false) String storeName, HttpSession httpSession) {
//    public ModelAndView listFoodIndex(Model model, @PathVariable (required = false) String storeName, HttpSession httpSession) {
//        List<Category> foodCategories = categoryService.getAllCategories();
//        model.addAttribute("foodCategories", foodCategories);
//
//        String storedStoreName = (String) httpSession.getAttribute("storeName");
//        log.info("storedStoreName => " + storedStoreName);
//        if ( storedStoreName == null) {
//            Map<String, String> response = new HashMap<>();
//            response.put("error", "로그인 부탁드립니다.");
//            return new ModelAndView("error", response, HttpStatus.UNAUTHORIZED);
//        } else {
//            return new ModelAndView("members/food/listFood");
//      }
//    }
//
//
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
