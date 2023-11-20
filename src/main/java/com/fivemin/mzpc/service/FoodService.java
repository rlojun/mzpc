package com.fivemin.mzpc.service;

import com.fivemin.mzpc.data.entity.Food;
import com.fivemin.mzpc.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final OrdersRepository ordersRepository;
    private final ToppingRepository toppingRepository;
    private final CartRepository cartRepository;

    @Autowired
    public FoodService(
            FoodRepository foodRepository,
            OrdersRepository ordersRepository,
            ToppingRepository toppingRepository,
            CartRepository cartRepository) {
        this.foodRepository = foodRepository;
        this.ordersRepository = ordersRepository;
        this.toppingRepository = toppingRepository;
        this.cartRepository = cartRepository;
    }

    public List<Food> getFoodByCategory(String category) {

        return foodRepository.findByCategoryName(category);
    }

}
