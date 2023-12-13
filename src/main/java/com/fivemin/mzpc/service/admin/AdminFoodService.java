package com.fivemin.mzpc.service.admin;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fivemin.mzpc.data.dto.CategoryDto;
import com.fivemin.mzpc.data.dto.FoodDto;
import com.fivemin.mzpc.data.entity.Category;
import com.fivemin.mzpc.data.entity.Food;
import com.fivemin.mzpc.data.repository.CategoryRepository;
import com.fivemin.mzpc.data.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.*;
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

    private final AmazonS3Client amazonS3Client;

    @Value(value = "${cloud.aws.s3.bucket}")
    private String bucket;

    @Value(value = "${cloud.aws.region.static}")
    private String region;

    @Autowired
    public AdminFoodService(FoodRepository foodRepository, CategoryRepository categoryRepository, EntityManager entityManager,AmazonS3Client amazonS3Client) {
        this.foodRepository = foodRepository;
        this.categoryRepository = categoryRepository;
        this.entityManager = entityManager;
        this.amazonS3Client = amazonS3Client;
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

        foodRepository.save(food);
        fileUpload(foodPicture, foodPicture.getOriginalFilename());
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

        if (foodPicture != null){
            deleteFile(updateFood.getPicture());
            fileUpload(foodPicture,fileName);
        }

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

    }

    @Transactional
    public void deleteFood(Long foodIdx) {

        Food food = foodRepository.findByIdx(foodIdx);
        foodRepository.deleteById(foodIdx);
        log.info("food_picture : {}",food.getPicture());
        deleteFile(food.getPicture());
    }

    public String fileUpload(MultipartFile foodPicture, String fileName) {
        String returnResult = "";
        if (foodPicture!=null){
            try {
                File uploadFile = convertMultiPartFileToFile(foodPicture);
                amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(
                        CannedAccessControlList.PublicRead));
                returnResult =  amazonS3Client.getUrl(bucket, fileName).toString();
            } catch (IOException e) {
                System.out.println("파일을 추가하는데 오류가 발생했습니다.");
                returnResult = null;
            }
        }
        return returnResult;
    }

    //MutipartFile을 File로 타입 변경 메서드
    private File convertMultiPartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        }
        return file;
    }

    public void deleteFile(String foodPicture) {

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .build();

        try {
            s3Client.deleteObject(new DeleteObjectRequest(bucket, foodPicture));
            System.out.println("파일이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            System.err.println("파일 삭제 중 오류가 발생했습니다: " + e.getMessage());
        }
    }




}
