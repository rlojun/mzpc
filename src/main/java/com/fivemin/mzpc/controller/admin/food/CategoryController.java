package com.fivemin.mzpc.controller.admin.food;


import com.fivemin.mzpc.data.dto.CategoryDto;
import com.fivemin.mzpc.service.admin.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
-기능
카테고리 리스트
카테고리 수정
    음식 카테고리 갯수 맥스 10개 설정 (갯수는 예시)
    음식 카테고리 추가 및 삭제, 수정 기능
 */
@Controller
@Slf4j
@RequestMapping("/{storeCode}") //관리자 pk
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 음식 카태고리가 뭐뭐 있는지 나타내고, 추가 수정 삭제에 따라 리스트가 바뀜
    @GetMapping(value = "/listcategory")
    public String listFoodCategory(@PathVariable String storeCode, Model model) {

        List<CategoryDto> listCategory = categoryService.getListCategory(storeCode);
        log.info("listCategory : {} ", listCategory);

        model.addAttribute("listCategroy", listCategory);
        model.addAttribute("storeCode", storeCode);

        return "/admin/category/listCategory";
    }

    @GetMapping(value = "/addCategoryForm")
    public String addFoodCategoryForm(@PathVariable String storeCode, Model model) {
        model.addAttribute("storeCode", storeCode);
        return "/admin/category/addCategoryForm";
    }

//    @GetMapping(value = "/modifyCategoryForm")
//    public String modifyCategoryForm(@PathVariable String storeCode, @RequestParam Long categoryIdx, Model model) {
//
//        CategoryDto categoryDto = categoryService.modifyCategoryForm(storeCode,categoryIdx);
//
//        log.info("categoryDto : {} ", categoryDto);
//        log.info("storeCode : {}", storeCode);
//        log.info("categoryIdx : {}", categoryIdx);
//
//        model.addAttribute("categoryDto", categoryDto);
//
//        return "/admin/category/modifyCategoryForm";
//    }
}

/*




    ********************************************
    modifyFoodCategoryForm
    (pk를 쓰지 않고 한화면에 모든 카테고리 수정 하는거 어떰? )
    *********************************************
    modifyFoodCategory
    (food category 수정 하는 메서드)

    deleteFoodCategory
    (food category 삭제 하는 메서드)

    */
