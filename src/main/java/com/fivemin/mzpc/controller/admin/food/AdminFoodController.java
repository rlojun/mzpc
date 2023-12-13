package com.fivemin.mzpc.controller.admin.food;

import com.fivemin.mzpc.data.dto.AdminDto;
import com.fivemin.mzpc.data.dto.CategoryDto;
import com.fivemin.mzpc.data.dto.FoodDto;
import com.fivemin.mzpc.data.dto.StoreDto;
import com.fivemin.mzpc.service.admin.AdminAdminService;
import com.fivemin.mzpc.service.admin.AdminCategoryService;
import com.fivemin.mzpc.service.admin.AdminFoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin/{storeCode}") //관리자 pk
public class AdminFoodController {

    private final AdminCategoryService adminCategoryService;

    private final AdminAdminService adminAdminService;

    private final AdminFoodService adminFoodService;

    @Autowired
    public AdminFoodController(AdminCategoryService adminCategoryService, AdminAdminService adminAdminService, AdminFoodService adminFoodService){
        this.adminCategoryService = adminCategoryService;
        this.adminAdminService = adminAdminService;
        this.adminFoodService = adminFoodService;
    }

    @GetMapping(value = "/food")
    public String listCategory(@PathVariable String storeCode,@RequestParam boolean topping, Model model){
        List<CategoryDto> listCategory = adminCategoryService.getListCategory(storeCode);
        StoreDto storeDto = adminCategoryService.getStore(storeCode);
        AdminDto adminDto = adminAdminService.getAdminName(storeDto.getIdx());
        List<FoodDto> foodDtos= adminFoodService.getFoodList(storeCode,topping);

        model.addAttribute("listCategory",listCategory);
        model.addAttribute("storeName",storeDto.getName());
        model.addAttribute("adminName",adminDto.getName());
        model.addAttribute("foods",foodDtos);

        String resultView = "";
        if (!topping) {
            resultView = "admin/food/listFood";
        } else {
            resultView = "admin/food/topping/toppingList";

        }
        return resultView;
    }

    @GetMapping(value = "/addFoodForm")
    public String addFoodForm(@PathVariable String storeCode, @RequestParam boolean topping , Model model){
        List<CategoryDto> categoryDtos = adminCategoryService.getListCategory(storeCode);
        model.addAttribute("storeCode", storeCode);
        model.addAttribute("categories",categoryDtos);

        String resultView = "";
        if (!topping) {
            resultView = "admin/food/addFoodForm";
        } else {
            resultView = "admin/food/topping/addToppingForm";

        }

        return resultView;
    }

    @GetMapping(value = "/addToppingForm")
    public String addToppingForm(@PathVariable String storeCode, Model model) {
        List<CategoryDto> categoryDtos = adminCategoryService.getListCategory(storeCode);
        model.addAttribute("storeCode", storeCode);
        model.addAttribute("categories",categoryDtos);

        return "admin/food/topping/addToppingForm";
    }

    @GetMapping(value = "/detailFood")
    public String detailFood(@PathVariable String storeCode, @RequestParam String categoryCode,
                             @RequestParam String foodCode, @RequestParam boolean topping,
                             Model model){
        FoodDto foodDto = adminFoodService.getFoodList(categoryCode,foodCode);
        model.addAttribute("food",foodDto);
        model.addAttribute("storeCode",storeCode);

        String resultView = "";
        if (!topping) {
            resultView = "detailFood";
        } else if (topping) {
            resultView = "topping/detailTopping";
        }

        return "admin/food/"+resultView;
    }
}