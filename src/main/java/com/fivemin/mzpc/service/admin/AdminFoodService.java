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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AdminFoodService {

    private final FoodRepository foodRepository;
    private final CategoryRepository categoryRepository;

    private final EntityManager entityManager;

    @Autowired
    public AdminFoodService(FoodRepository foodRepository, CategoryRepository categoryRepository, EntityManager entityManager) {
        this.foodRepository = foodRepository;
        this.categoryRepository = categoryRepository;
        this.entityManager = entityManager;
    }

    public List<FoodDto> getFoodList(String storeCode, boolean topping) {
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

    @Transactional
    public void addFood(FoodDto foodDto, MultipartFile foodPicture,String categoryCode) {
        Category category = categoryRepository.findByCode(categoryCode);

        Food food = Food.builder()
                .idx(foodDto.getIdx())
                .code(makeCode())
                .name(foodDto.getName())
                .picture(foodPicture.getOriginalFilename())
                .price(foodDto.getPrice())
                .description(foodDto.getDescription())
                .stock(foodDto.getStock())
                .topping(foodDto.isTopping())
                .category(category)
                .build();

        fileUpload(foodPicture);

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
    public void modifyFood(FoodDto foodDto,String categoryName,MultipartFile foodPicture) {
        Category category = categoryRepository.findByName(categoryName);
        Food updateFood = foodRepository.findById(foodDto.getIdx()).orElse(null);
        String fileName = foodPicture==null? updateFood.getPicture() : foodPicture.getOriginalFilename();

        updateFood.setIdx(foodDto.getIdx());
        updateFood.setCode(foodDto.getCode());
        updateFood.setName(foodDto.getName());
        updateFood.setPrice(foodDto.getPrice());
        updateFood.setPicture(fileName);
        updateFood.setDescription(foodDto.getDescription());
        updateFood.setStock(foodDto.getStock());
        updateFood.setTopping(foodDto.isTopping());
        updateFood.setCategory(category);

        entityManager.merge(updateFood);

        fileUpload(foodPicture);

    }

    @Transactional
    public void deleteFood(Long foodIdx) {
        foodRepository.deleteById(foodIdx);
    }

    private void fileUpload(MultipartFile foodPicture) {
        //파일이 있는지 없는지 판단
        if (foodPicture!=null) {
            String fileName = StringUtils.cleanPath(foodPicture.getOriginalFilename());

            String relativePath = "/bootstrap/images/";

            try {
                Path uploadPath = Paths.get(relativePath);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                try (InputStream inputStream = foodPicture.getInputStream()) {
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (Exception e) {
                System.out.println("fileUpload() Err --> " + e.getMessage());
            }
        }
    }

}
