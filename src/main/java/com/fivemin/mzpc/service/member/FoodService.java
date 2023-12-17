package com.fivemin.mzpc.service.member;

import com.fivemin.mzpc.data.dto.FavoritesDto;
import com.fivemin.mzpc.data.dto.FoodDto;
import com.fivemin.mzpc.data.entity.Favorites;
import com.fivemin.mzpc.data.entity.Food;
import com.fivemin.mzpc.data.entity.Members;
import com.fivemin.mzpc.data.repository.FavoritesRepository;
import com.fivemin.mzpc.data.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("memberFoodService")
@Slf4j
public class FoodService {

    private final FoodRepository foodRepository;
    private final FavoritesRepository favoritesRepository;

    @Autowired
    public FoodService(
            FoodRepository foodRepository, FavoritesRepository favoritesRepository) {
        this.foodRepository = foodRepository;
        this.favoritesRepository = favoritesRepository;
    }

    public List<FoodDto> getFoodList(String storeName) {

        List<Food> foodList = foodRepository.findByStoreName(storeName);
        List<FoodDto> foodDtoList = new ArrayList<>();
        for (Food food : foodList) {
            FoodDto foodDto = FoodDto.builder()
                    .idx(food.getIdx())
                    .code(food.getCode())
                    .name(food.getName())
                    .price(food.getPrice())
                    .picture("https://mzpc-s3-bucket.s3.ap-northeast-2.amazonaws.com/"+food.getPicture())
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
                    .topping(food.isTopping())
                    .storeName(food.getCategory().getStore().getName())
                    .categoryName(food.getCategory().getName())
                    .build();
        }
        return null;
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
        return foodRepository.findToppingByCategoryName(categoryName, true)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<FoodDto> filterFoodByToppings(List<FoodDto> foodDtoList) {
        return foodDtoList.stream()
                .filter(foodDto -> !foodDto.isTopping())
                .collect(Collectors.toList());
    }

    public List<String> createDistinctCategories(List<FoodDto> filteredFoodList) {
        List<String> distinctFoodCategories = filteredFoodList.stream()
                .map(FoodDto::getCategoryName)
                .distinct()
                .collect(Collectors.toList());
        log.info("distinctFoodCategories: {}", distinctFoodCategories);
        return distinctFoodCategories;
    }

    public void addFavorite(Long foodIdx, Members members) {
        Food food = foodRepository.getByFoodIdx(foodIdx);
        log.info("foodIdx : {}", foodIdx);
        log.info("members : {}", members);

        Favorites favorites = new Favorites();
            favorites.setFood(food);
            favorites.setMembers(members);
            favorites.setCode(favorites.getCode());

            favoritesRepository.save(favorites);
    }

    public List<FavoritesDto> getFavorites(Long memberIdx) {
        return favoritesRepository.findFavoritesByMemberIdx(memberIdx)
                .stream()
                .map(this::convertToFavoritesDto)
                .collect(Collectors.toList());
    }

    public FavoritesDto convertToFavoritesDto(Favorites favorites) {
        return FavoritesDto.builder()
                .idx(favorites.getIdx())
                .code(favorites.getCode())
                .food(favorites.getFood())
                .members(favorites.getMembers())
                .build();
    }

    public boolean isFoodInFavorites(Long foodIdx, Members members) {
        return favoritesRepository.existsByFoodIdxAndMembersIdx(foodIdx, members.getIdx());
    }

    @Transactional
    public void removeFavorite(Long favoriteIdx) {
        favoritesRepository.removeByIdx(favoriteIdx);
    }
}
