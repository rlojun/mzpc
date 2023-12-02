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

    public List<CartDto> getCartByMemberIdx(Long memberIdx) {
        return cartRepository.getCartByMemberIdx(memberIdx);
    }

    public List<CartDto> addToCart(List<CartDto> cartItems, HttpSession httpSession, String foodCode) {
        CartDto cartItem = new CartDto();
        Members members = (Members) httpSession.getAttribute("members");
        Food food = foodRepository.getByFoodCode(foodCode);
        log.info("foodCode: {}", food.getCode());

        Cart cart = new Cart();
        cart.setFood(food);
        cart.setMembers(members);
        cart.setPayments("현금");
        cart.setCode(cart.generateUniqueCode());

        log.info("CartService - addToCart: cartFood: {}", cart.getFood().getName());
        log.info("CartService - addToCart: cartIdx: {}", cart.getIdx());
        log.info("CartService - addToCart: cartCode: {}", cart.getCode());
        log.info("CartService - addToCart: cartMember: {}", cart.getMembers().getId());
        log.info("CartService - addToCart: cartPayment: {}", "현금");

        cart = cartRepository.save(cart);

        boolean itemExists = cartItems.stream()
                .anyMatch(item -> item.getFood().getIdx().equals(food.getIdx()));

        if (!itemExists) {

            cartItem.setIdx(cart.getIdx());
            cartItem.setFood(cart.getFood());
            cartItem.setMember(cart.getMembers());
            cartItem.setPayments(cart.getPayments());

            cartItems.add(cartItem);
            log.info("CartService - addToCart: cartItems {} :", cartItems);

        }

        return cartItems;
    }
}
