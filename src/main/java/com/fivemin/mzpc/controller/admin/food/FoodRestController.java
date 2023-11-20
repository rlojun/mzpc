package com.fivemin.mzpc.controller.admin.food;

import com.fivemin.mzpc.data.entity.Food;
import com.fivemin.mzpc.service.admin.AdminCategoryService;
import com.fivemin.mzpc.service.admin.AdminFoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/category")
public class FoodRestController {

    private AdminFoodService adminFoodService;

    private AdminCategoryService adminCategoryService;

    @Autowired
    public FoodRestController(AdminFoodService adminFoodService, AdminCategoryService adminCategoryService){
        this.adminFoodService = adminFoodService;
        this.adminCategoryService = adminCategoryService;
    }

    @GetMapping(value = "/food")
    private ResponseEntity<List<Food>> getListFood(@RequestParam("categoryName") String name,
                                               Model model){

        List<Food> listFood = adminFoodService.getListFoodByName(name);

        return ResponseEntity.ok(listFood);
    }
    
    @PostMapping(value = "/addCategory")
    private ResponseEntity<String> addCategory(@RequestParam String categoryName,
                                               @RequestParam String adminCode) {
        
        log.info("categoryName : {}",categoryName);
        log.info("adminCode : {}",adminCode);

        adminCategoryService.addCategory(categoryName,adminCode);

        return ResponseEntity.ok("success"+ categoryName);
    }
}
