package com.fivemin.mzpc.controller.admin.food;

import com.fivemin.mzpc.data.dto.FoodDto;
import com.fivemin.mzpc.service.admin.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/food")
@Slf4j
public class FoodRestController {

    private final FoodService foodService;

    @Autowired
    public FoodRestController(FoodService foodService) {
        this.foodService = foodService;
    }
    @PostMapping(value = "/addFood")
    public ResponseEntity<String> addFood(@RequestBody FoodDto foodDto, @RequestParam String categoryCode) {
        foodService.addFood(foodDto,categoryCode);
        log.info("foodDto : {}", foodDto);
        log.info("categoryCode : {}", categoryCode);

        if (!foodDto.isTopping()) {
            return ResponseEntity.ok("음식이 추가 되었습니다.");
        } else {
            return ResponseEntity.ok("토핑이 추가 되었습니다.");
        }
    }
}
