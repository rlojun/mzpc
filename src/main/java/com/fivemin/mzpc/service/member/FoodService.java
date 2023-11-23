package com.fivemin.mzpc.service.member;

import com.fivemin.mzpc.data.dto.MenuDto;
import com.fivemin.mzpc.data.entity.Food;
import com.fivemin.mzpc.data.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("memberFoodService")
@Slf4j
public class FoodService {

    private final FoodRepository foodRepository;
    private final OrdersRepository ordersRepository;
    private final CartRepository cartRepository;
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public FoodService(
            FoodRepository foodRepository,
            OrdersRepository ordersRepository,
            CartRepository cartRepository, StoreRepository storeRepository, CategoryRepository categoryRepository) {
        this.foodRepository = foodRepository;
        this.ordersRepository = ordersRepository;
        this.cartRepository = cartRepository;
        this.storeRepository = storeRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<MenuDto> getListMenu(String storeName) {

        List<Food> foodList = foodRepository.findByStoreName(storeName);
        log.info("storeName: {} ", storeName);
        log.info("Size of foodList: {}", foodList.size());

        List<MenuDto> menuDtoList = new ArrayList<>();

        for (Food food : foodList) {
            MenuDto menuDto = MenuDto.builder()
                    .idx(food.getIdx())
                    .code(food.getCode())
                    .name(food.getName())
                    .price(food.getPrice())
                    .picture(food.getPicture())
                    .description(food.getDescription())
                    .categoryIdx(food.getCategory().getIdx())
                    .categoryCode(food.getCategory().getCode())
                    .categoryName(food.getCategory().getName())
                    .build();
            menuDtoList.add(menuDto);
        }

        return menuDtoList;
    }

}
