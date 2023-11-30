package com.fivemin.mzpc.controller.admin.food;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fivemin.mzpc.data.dto.FoodDto;
import com.fivemin.mzpc.service.admin.AdminFoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public ResponseEntity<String> addFood(@RequestPart MultipartFile foodPicture,
                                          @RequestParam ("foodDto") String foodDtoJson,
                                          @RequestParam String categoryCode) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            FoodDto foodDto = objectMapper.readValue(foodDtoJson, FoodDto.class);
            log.info("categoryCode : {}", categoryCode);
            log.info("foodDto : {}", foodDto);
            log.info("foodPicture : {}", foodPicture.getOriginalFilename());
            adminFoodService.addFood(foodDto,foodPicture,categoryCode);

        } catch (IOException e) {
            System.out.println("addFood() Err --> "+ e.getMessage());
        }
        return ResponseEntity.ok("음식이 추가되었습니다.");

    }

    @PostMapping(value = "/modifyFood")
    public ResponseEntity<String> modifyFood(@RequestPart MultipartFile foodPicture,
                                             @RequestParam ("foodDto") String foodDtoJson,
                                             @RequestParam String categoryName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            FoodDto foodDto = objectMapper.readValue(foodDtoJson, FoodDto.class);
            log.info("foodPicture : {}", foodPicture.getOriginalFilename());
            log.info("foodDtoJson : {}",foodDto);
            log.info("categoryName : {}",categoryName);

        adminFoodService.modifyFood(foodDto,categoryName,foodPicture);

        } catch (IOException e) {
            System.out.println("modifyFood() Err --> "+ e.getMessage());
        }
        return ResponseEntity.ok("업데이트 되었습니다.");
    }

    @DeleteMapping(value = "/deleteFood")
    private ResponseEntity<String> deleteFood(@RequestBody Long foodIdx) {
        adminFoodService.deleteFood(foodIdx);
        return ResponseEntity.ok("삭제되었습니다.");
    }
}
