//package com.fivemin.mzpc.controller.admin.food;
//
//import com.fivemin.mzpc.data.dto.CategoryDto;
//import com.fivemin.mzpc.data.dto.FoodDto;
//import com.fivemin.mzpc.service.admin.CategoryService;
//import com.fivemin.mzpc.service.admin.FoodService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@Slf4j
//@RequestMapping(value = "/category")
//public class CategoryRestController {
//
//    private final FoodService foodService;
//
//    private final CategoryService categoryService;
//
//    @Autowired
//    public CategoryRestController(FoodService foodService, CategoryService categoryService){
//        this.foodService = foodService;
//        this.categoryService = categoryService;
//    }
//
//    @GetMapping(value = "/food")
//    private ResponseEntity< List<FoodDto>> getListFood(@RequestParam String categoryName){
//
//        log.info("getListFood() ==> ");
//
//        List<FoodDto> listFood = foodService.getListFoodByName(categoryName);
//
//        return ResponseEntity.ok(listFood);
//    }
//
//    @PostMapping(value = "/addCategory")
//    private ResponseEntity<String> addCategory(@RequestBody CategoryDto categoryDto,@RequestParam String storeCode) {
//
//        log.info("categoryName : {}",categoryDto);
//        log.info("storeCode:{}",storeCode);
//
//        categoryService.addCategory(categoryDto,storeCode);
//
//        return ResponseEntity.ok("카테고리가 추가 되었습니다.");
//    }
//
//    @PutMapping(value = "/modifyCategory")
//    private ResponseEntity<String> modifyCategory(@RequestBody CategoryDto categoryDto) {
//
//        categoryService.modifyCategory(categoryDto);
//
//        log.info("categoryDto : {}",categoryDto);
//
//        return ResponseEntity.ok("이름이 변경되었습니다.");
//    }
//
//    @DeleteMapping(value = "deleteCategory")
//    private ResponseEntity<String> deleteCategory(@RequestBody Long categoryIdx) {
//
//        categoryService.deleteCategory(categoryIdx);
//
//        return ResponseEntity.ok("삭제되었습니다.");
//    }
//}
