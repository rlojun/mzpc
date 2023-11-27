package com.fivemin.mzpc.controller.admin.food;

import com.fivemin.mzpc.data.dto.FoodDto;
import com.fivemin.mzpc.service.admin.AdminFoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/food")
@Slf4j
public class AdminFoodRestController {

    private final AdminFoodService adminFoodService;

    @Autowired
    public AdminFoodRestController(AdminFoodService adminFoodService) {
        this.adminFoodService = adminFoodService;
    }
    @PostMapping(value = "/addFood")
    public ResponseEntity<String> addFood(@RequestBody FoodDto foodDto, @RequestParam String categoryCode) {
        adminFoodService.addFood(foodDto,categoryCode);
        log.info("foodDto : {}", foodDto);
        log.info("categoryCode : {}", categoryCode);

        if (!foodDto.isTopping()) {
            return ResponseEntity.ok("음식이 추가 되었습니다.");
        } else {
            return ResponseEntity.ok("토핑이 추가 되었습니다.");
        }
    }

    @PostMapping(value = "/modifyFood")
    public ResponseEntity<String> modifyFood(@RequestBody FoodDto foodDto,@RequestParam String categoryName) {
        adminFoodService.modifyFood(foodDto,categoryName);
        log.info("foodDto : {}", foodDto);
        log.info("categoryName : {}",categoryName);
        return ResponseEntity.ok("업데이트 되었습니다.");
    }
}
