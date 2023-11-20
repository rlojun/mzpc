package com.fivemin.mzpc.controller.admin.food;

import com.fivemin.mzpc.data.entity.Category;
import com.fivemin.mzpc.data.entity.Food;
import com.fivemin.mzpc.service.admin.CategoryService;
import com.fivemin.mzpc.service.admin.FoodService;
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

    private FoodService foodService;

    private CategoryService categoryService;

    @Autowired
    public FoodRestController(FoodService foodService, CategoryService categoryService){
        this.foodService = foodService;
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/food")
    private ResponseEntity<List<Food>> getListFood(@RequestParam("categoryName") String name,
                                               Model model){

        List<Food> listFood = foodService.getListFoodByName(name);

        return ResponseEntity.ok(listFood);
    }
    
    @PostMapping(value = "/addCategory")
    private ResponseEntity<String> addCategory(@RequestParam String categoryName,
                                               @RequestParam String adminCode) {
        
        log.info("categoryName : {}",categoryName);
        log.info("adminCode : {}",adminCode);

        categoryService.addCategory(categoryName,adminCode);

        return ResponseEntity.ok("success"+ categoryName);
    }
}
