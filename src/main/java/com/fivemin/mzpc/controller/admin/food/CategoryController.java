package com.fivemin.mzpc.controller.admin.food;


import com.fivemin.mzpc.data.dto.CategoryDto;
import com.fivemin.mzpc.service.admin.AdminCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/{adminCode}") //관리자 pk
public class CategoryController {

    @Autowired
    private AdminCategoryService adminCategoryService;

    // 음식 카태고리가 뭐뭐 있는지 나타내고, 추가 수정 삭제에 따라 리스트가 바뀜
    @GetMapping(value = "/listcategory")
    public String listFoodCategory(@PathVariable String adminCode, Model model) {

        List<CategoryDto> listCategory = adminCategoryService.getListCategory(adminCode);
        log.info("listCategory : {} ", listCategory);

        model.addAttribute("listCateogry", listCategory);
        model.addAttribute("adminCode", adminCode);

        return "/admin/category/listCategory";
    }

    @GetMapping(value = "/addCategoryForm")
    public String addFoodCategoryForm(@PathVariable String adminCode, Model model) {

        log.info("addFoodCategoryForm");
        model.addAttribute("adminCode", adminCode);
        return "/admin/category/addCategoryForm";
    }
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
