package com.fivemin.mzpc.service.admin;

import com.fivemin.mzpc.data.dto.CategoryDto;
import com.fivemin.mzpc.data.dto.FoodDto;
import com.fivemin.mzpc.data.entity.Category;
import com.fivemin.mzpc.data.entity.Food;
import com.fivemin.mzpc.data.repository.CategoryRepository;
import com.fivemin.mzpc.data.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AdminFoodService {

    private final FoodRepository foodRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public AdminFoodService(FoodRepository foodRepository, CategoryRepository categoryRepository) {
        this.foodRepository = foodRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<FoodDto> getFoodList(String storeCode,boolean topping) {
        List<Food> foods = foodRepository.findByStoreCode(storeCode,topping);
        List<FoodDto> foodDtos = new ArrayList<>();

        for ( Food food : foods) {
            CategoryDto categoryDto = CategoryDto.builder()
                    .idx(food.category.getIdx())
                    .code(food.category.getCode())
                    .name(food.category.getName())
                    .build();

            FoodDto foodDto = FoodDto.builder()
                    .idx(food.getIdx())
                    .code(food.getCode())
                    .name(food.getName())
                    .price(food.getPrice())
                    .picture(food.getPicture())
                    .description(food.getDescription())
                    .stock(food.getStock())
                    .topping(food.isTopping())
                    .categoryDto(categoryDto)
                    .build();
            foodDtos.add(foodDto);
        }

        return foodDtos;
    }


    public List<FoodDto> getListFoodByName(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        log.info("categoryIdx :{} ",category.getIdx());
        List<Food> foodList = foodRepository.findByCategoryIdx(category.getIdx());
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
                    .topping(foods.isTopping())
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

    public FoodDto getFoodList(String categoryCode, String foodCode) {
        Food food = foodRepository.findByCode(categoryCode,foodCode);

        CategoryDto categoryDto = CategoryDto.builder()
                .idx(food.getCategory().getIdx())
                .code(food.getCategory().getCode())
                .name(food.getCategory().getName())
                .build();

        FoodDto foodDto = FoodDto.builder()
                .idx(food.getIdx())
                .code(food.getCode())
                .name(food.getName())
                .picture(food.getPicture())
                .price(food.getPrice())
                .description(food.getDescription())
                .stock(food.getStock())
                .topping(food.isTopping())
                .createdAt(food.getCreatedAt())
                .updateAt(food.getUpdatedAt())
                .categoryDto(categoryDto)
                .build();
        return foodDto;
    }

    @Transactional
    public void modifyFood(FoodDto foodDto,String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        Food food = foodRepository.findById(foodDto.getIdx()).orElse(null);

        if(food != null){
            Food updateFood = Food.builder()
                    .idx(foodDto.getIdx())
                    .code(foodDto.getCode())
                    .name(foodDto.getName())
                    .picture(foodDto.getPicture())
                    .price(foodDto.getPrice())
                    .description(foodDto.getDescription())
                    .stock(foodDto.getStock())
                    .topping(foodDto.isTopping())
                    .category(category)
                    .build();

            foodRepository.save(updateFood);
        }
    }

    @Transactional
    public void deleteFood(Long foodIdx) {
        foodRepository.deleteById(foodIdx);
    }
}
