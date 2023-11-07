package com.fivemin.mzpc.controller.admin.food;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
-기능
카테고리 리스트
카테고리 수정
    음식 카테고리 갯수 맥스 10개 설정 (갯수는 예시)
    음식 카테고리 추가 및 삭제, 수정 기능
 */
@Controller
@RequestMapping("/{adminId}/category") //관리자 pk
public class CategoryController {

    /*

    // 음식 카태고리가 뭐뭐 있는지 나타내고, 추가 수정 삭제에 따라 리스트가 바뀜
    listFoodCategory


    addFoodCategory
    (food category 추가 하는 메서드
    * 카테고리 갯수 max10 설정)



    ********************************************
    modifyFoodCategoryForm
    (pk를 쓰지 않고 한화면에 모든 카테고리 수정 하는거 어떰? )
    *********************************************
    modifyFoodCategory
    (food category 수정 하는 메서드)

    deleteFoodCategory
    (food category 삭제 하는 메서드)

    */
}