package com.fivemin.mzpc.controller.admin.food;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
- 기능
카테고리 별 음식 상품 목록
    화면에 갯수 넘어가면 스크롤
음식 상품 상세보기
    주문시 재고량 빠지는 기능
    이건 member쪽일듯 - 원식
음식 상품 수정
    사진 및 재고량 등록
음식 상품 삭제
음식 상품 추가
 */
@Controller
@Slf4j
@RequestMapping("/{adminCode}/food") //관리자 pk
public class AdminFoodController {

    // 카테고리별 음상 상품 리스트
    @GetMapping("/{categoryId}")
    public String listFoodCategory() {
        return "";
    }

    // 즐겨찾기 리스트
    @GetMapping("/favorites")
    public String listFoodFavorites() {
        return "";
    }

    // 토핑 리스트
    @GetMapping("/topping")
    public String listTopping(){return "";}
    /*

    addFoodForm
    (음식 상품 추가 화면 이동 메서드)

    addFood
    (음식 상품 추가 등록 확인 메서드)

    detailFood
    (음식 상품 상세 화면 이동 메서드)

    returnFoodList
    (음식 상세 화면에서 음식 상품 목록으로 돌아가기 메서드)     // > 삭제 예정

    modifyFoodForm
    (음식 상품 수정 화면으로 이동하는 메서드)

    modifyFood
    (음식 상품 수정 확인 메서드)

    deleteFood
    (음식 상품 삭제 확인 메서드)

     */
}
