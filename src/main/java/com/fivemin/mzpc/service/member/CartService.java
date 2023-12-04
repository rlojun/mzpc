package com.fivemin.mzpc.service.member;

import com.fivemin.mzpc.data.dto.CartDto;
import com.fivemin.mzpc.data.entity.Cart;
import com.fivemin.mzpc.data.entity.Food;
import com.fivemin.mzpc.data.entity.Members;
import com.fivemin.mzpc.data.repository.CartRepository;
import com.fivemin.mzpc.data.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

//    public List<CartDto> getCartByMemberIdx(Long memberIdx) {
//
//        List<Cart> cartList = cartRepository.findCartByMemberIdx(memberIdx);
//
//        return CartDto.builder()
//                .cartPrice(cartList.get)
//        List<CartDto> cartDtoList = cartList.
//    }

    // post topping changes
//    public CartDto addToCart(CartDto cartItems, HttpSession httpSession, String mainFoodCode, Cart cartList) {
//        Members members = (Members) httpSession.getAttribute("members");
//        Food food = foodRepository.getByFoodCode(mainFoodCode);
//        List<Food> foodList = new ArrayList<>();
//        foodList.add(food);
//
//        Cart cart = new Cart();
//        log.info("foodCode: {}", food.getCode());
//
//        if (cartItems == null) {
//            cartItems = CartDto.builder()
//                    .code(cart.generateUniqueCode())
//                    .payments("현금")
//                    .food(foodList)
//                    .member(members)
//                    .memberIdx(members.getIdx())
//                    .storeName(food.getCategory().getStore().getName())
//                    .build();
//        }
//        cartItems.getFood().add(food);
//
//        return cartItems;
//    }

// pre topping changes
    public CartDto addToCart(CartDto cartItems, HttpSession httpSession, String foodCode, String selectedToppings) {

        Members members = (Members) httpSession.getAttribute("members");
        Food mainFood = foodRepository.getByFoodCode(foodCode);
        Food topping = foodRepository.getByFoodName(selectedToppings);
        List<Food> foodList = new ArrayList<>();
        foodList.add(mainFood);
        foodList.add(topping);

        Cart cart = new Cart();

        if (cartItems == null) {
            cartItems = CartDto.builder()
                    .code(cart.generateUniqueCode())
                    .payments("현금")
                    .food(foodList)
                    .member(members)
                    .memberIdx(members.getIdx())
                    .storeName(mainFood.getCategory().getStore().getName())
                    .build();
        }
        cartItems.getFood().add(mainFood);

        return cartItems;
    }


//    public List<CartDto> addToCart(List<CartDto> cartItems, HttpSession httpSession, String foodCode) {
//        CartDto cartItem = new CartDto();
//        Members members = (Members) httpSession.getAttribute("members");
//        Food food = foodRepository.getByFoodCode(foodCode);
//        log.info("foodCode: {}", food.getCode());
//
//        Cart cart = new Cart();
//        cart.setFood(food);
//        cart.setMembers(members);
//        cart.setPayments("현금");
//        cart.setCode(cart.generateUniqueCode());
//        cart = cartRepository.save(cart);
//
//        boolean itemExists = cartItems.stream()
//                .anyMatch(item -> item.getFood().getIdx().equals(food.getIdx()));
//
//        if (!itemExists) {
//
//            cartItem.setIdx(cart.getIdx());
//            cartItem.setFood(cart.getFood());
//            cartItem.setMember(cart.getMembers());
//            cartItem.setPayments(cart.getPayments());
//
//            cartItems.add(cartItem);
//            log.info("CartService - addToCart: cartItems {} :", cartItems);
//
//        }
//
//        return cartItems;
//    }
}
