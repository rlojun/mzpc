package com.fivemin.mzpc.service.admin;

import com.fivemin.mzpc.data.dto.FoodDto;
import com.fivemin.mzpc.data.entity.Category;
import com.fivemin.mzpc.data.entity.Food;
import com.fivemin.mzpc.data.repository.CategoryRepository;
import com.fivemin.mzpc.data.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FoodService {

    private final FoodRepository foodRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository, CategoryRepository categoryRepository) {
        this.foodRepository = foodRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<FoodDto> getListFoodByName(String categoryName) {
        Long categoryIdx = categoryRepository.findByName(categoryName);
        log.info("categoryIdx :{} ",categoryIdx);
        List<Food> foodList = foodRepository.findByCategoryIdx(categoryIdx);
       List<FoodDto> foodDtoList = new ArrayList<>();

       log.info("foodList : {} ",foodList);

        for(Food foods : foodList) {
            FoodDto foodDto = FoodDto.builder()
                    .idx(foods.getIdx())
                    .code(foods.getCode())
                    .name(foods.getName())
                    .price(foods.getPrice())
                    .picture(foods.getPicture())
                    .description(foods.getDescription())
                    .topping(foods.getTopping())
                    .build();
            foodDtoList.add(foodDto);
        }

        log.info("foodDtoList : {}",foodDtoList);
        return foodDtoList;
    }

    public void addFood(FoodDto foodDto, String categoryCode) {
        Category category = categoryRepository.findByCode(categoryCode);

        Food food = Food.builder()
                .idx(foodDto.getIdx())
                .code(makeCode())
                .name(foodDto.getName())
                .picture(foodDto.getPicture())
                .price(foodDto.getPrice())
                .description(foodDto.getDescription())
                .stock(foodDto.getStock())
                .topping(foodDto.isTopping())
                .category(category)
                .build();

        foodRepository.save(food);
    }

    private String makeCode(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'F'HHMMyyyymmddss");
        return currentDateTime.format(formatter);
    }
}
