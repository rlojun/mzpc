package com.fivemin.mzpc.service.member;

import com.fivemin.mzpc.data.dto.CartDto;
import com.fivemin.mzpc.data.entity.Cart;
import com.fivemin.mzpc.data.entity.Food;
import com.fivemin.mzpc.data.repository.CartRepository;
import com.fivemin.mzpc.data.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CartService {

    private final CartRepository cartRepository;
    private final FoodRepository foodRepository;

    @Autowired
    public CartService(
            CartRepository cartRepository, FoodRepository foodRepository) {
        this.cartRepository = cartRepository;
        this.foodRepository = foodRepository;
    }

    public List<CartDto> addToCart(List<CartDto> cartItems, Food food) {
        CartDto cartItem = new CartDto();
        cartItem.setFood(food);

        cartItems.add(cartItem);
        log.info("CartService - addToCart: cartItems {} :", cartItems);

        saveCartInfo(cartItem);

        return cartItems;
    }



    private void saveCartInfo(CartDto cartItem) {

        Food food = foodRepository.getByFoodIdx(cartItem.getFood().getIdx());
        Cart cartEntity = new Cart();
        cartEntity.setFood(food);
        cartRepository.save(cartEntity);
    }

    //    private void saveCartInfo(CartFoodDto cartItem) {
//        Cart cartEntity = new Cart();
//        cartEntity.setFood(cartItem.getFood());
//
//        cartRepository.save(cartEntity);
//    }

//    public void addToCart(CartFoodDto cartItem, String foodName, Integer price) {
//        cartItem.setName(foodName);
//        cartItem.setPrice(price);
//        log.info("CartService - addToCart: cartItem {} :", cartItem);
//
//        saveCartInfo(cartItem);
//    }

//    public List<CartFoodDto> getCartByMemberCode (String memberCode) {
//        return cartRepository.getCartByMemberCode(memberCode);
//    }

    public List<CartDto> getCartByMemberIdx (Long memberIdx) {
        return cartRepository.getCartByMemberIdx(memberIdx);
    }

    }
