package com.fivemin.mzpc.controller.Member.food;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
-기능
카테고리 별 음식 상품 리스트
즐겨찾기 기능
    즐겨찾기 목록
음식 상세보기
    음식 별 토핑 목록 기능, 토핑 선택
 */
@Controller
public class MemberFoodController {

    @GetMapping("/favorites")
    public String foodListFavorites() {
        return "";
    }

//    @GetMapping("/{categoryId}")
//    public String listFoodCategory() {
//        return "";
//    }
//
//
//    // topping : true / false 로 구별 ( 토핑 / 일반 음식 )
//    @GetMapping("/{topping}")
//    public String listTopping() {
//        return "";
//    }

    /*
    addFoodFavorites
    (음식 즐겨 찾기 설정 하기)

    deleteFoodFavorites
    (음식 즐겨 찾기 제거 하기)

    detailFood
    (음식 상품 선택하기)

**************************************** 삭제 예정
    returnFoodList
    (음식 상세 화면 에서 음식 상품 목록 으로 돌아 가기 메서드)

     */

}
