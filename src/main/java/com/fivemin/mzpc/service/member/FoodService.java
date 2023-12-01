package com.fivemin.mzpc.service.member;

import com.fivemin.mzpc.data.dto.FoodDto;
import com.fivemin.mzpc.data.entity.Food;
import com.fivemin.mzpc.data.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("memberFoodService")
@Slf4j
public class FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(
            FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<FoodDto> getFoodList(String storeName) {

        List<Food> foodList = foodRepository.findByStoreName(storeName);
        log.info("storeName: {} ", storeName);
        log.info("Size of foodList: {}", foodList.size());

        List<FoodDto> foodDtoList = new ArrayList<>();

        for (Food food : foodList) {
            FoodDto foodDto = FoodDto.builder()
                    .idx(food.getIdx())
                    .code(food.getCode())
                    .name(food.getName())
                    .price(food.getPrice())
                    .picture(food.getPicture())
                    .description(food.getDescription())
                    .categoryIdx(food.getCategory().getIdx())
                    .categoryCode(food.getCategory().getCode())
                    .categoryName(food.getCategory().getName())
                    .topping(food.isTopping())
                    .build();
            foodDtoList.add(foodDto);
        }

        return foodDtoList;
    }

    public FoodDto getFoodDetails(String foodCode) {
        Food food = foodRepository.getByFoodCode(foodCode);

        if (food != null) {
            return FoodDto.builder()
                    .idx(food.getIdx())
                    .code(food.getCode())
                    .name(food.getName())
                    .price(food.getPrice())
                    .picture(food.getPicture())
                    .description(food.getDescription())
                    .storeName(food.getCategory().getStore().getName())
                    .categoryName(food.getCategory().getName())
                    .build();
        }
        return null;
    }

    public Food convertDtoToEntity(FoodDto foodDto) {
        Food food = new Food();
        food.setName(foodDto.getName());
        food.setPrice(foodDto.getPrice());
        food.isTopping();
        log.info(food.getName());
        log.info("price: {}", food.getPrice());
        return food;
    }

    public FoodDto convertToDto(Food food) {
        return FoodDto.builder()
                .idx(food.getIdx())
                .code(food.getCode())
                .name(food.getName())
                .picture(food.getPicture())
                .price(food.getPrice())
                .description(food.getDescription())
                .stock(food.getStock())
                .topping(food.isTopping())
                .storeName(food.category.getStore().getName())
                .createdAt(food.getCreatedAt())
                .category(food.getCategory())
                .categoryCode(food.getCategory().getCode())
                .categoryName(food.getCategory().getName())
                .categoryIdx(food.getCategory().getIdx())
                .build();
    }

    public List<FoodDto> getToppingsByCategory(String categoryName) {
        return foodRepository.findByCategoryNameAndTopping(categoryName, true)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<FoodDto> filterFoodByToppings(List<FoodDto> foodDtoList) {
        List<FoodDto> filteredFoodDtoList = foodDtoList.stream()
                .filter(foodDto -> !foodDto.isTopping())
                .collect(Collectors.toList());
        log.info("topping check : " + filteredFoodDtoList);
        return filteredFoodDtoList;
    }

    public List<String> createDistinctCategories(List<FoodDto> filteredFoodList) {
        List<String> distinctFoodCategories = filteredFoodList.stream()
                .map(FoodDto::getCategoryName)
                .distinct()
                .collect(Collectors.toList());
        log.info("distinctFoodCategories: {}", distinctFoodCategories);
        return distinctFoodCategories;
    }
}
