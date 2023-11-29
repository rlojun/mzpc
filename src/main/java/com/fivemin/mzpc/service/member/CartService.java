package com.fivemin.mzpc.service.member;

import com.fivemin.mzpc.data.dto.CartFoodDto;
import com.fivemin.mzpc.data.repository.CartRepository;
import com.fivemin.mzpc.data.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CartService {

    private final FoodRepository foodRepository;
    private final CartRepository cartRepository;

    @Autowired
    public CartService(
            FoodRepository foodRepository,
            CartRepository cartRepository) {
        this.foodRepository = foodRepository;
        this.cartRepository = cartRepository;
    }

    public List<CartFoodDto> addToCart(List<CartFoodDto> cartItems, String foodName, String foodCode, Integer price) {
        CartFoodDto cartItem = new CartFoodDto();
        cartItem.setName(foodName);
        cartItem.setCode(foodCode);
        cartItem.setPrice(price);
        cartItems.add(cartItem);
        log.info("CartService - addToCart: cartItems {} :", cartItems);

        return cartItems;

    }

}
