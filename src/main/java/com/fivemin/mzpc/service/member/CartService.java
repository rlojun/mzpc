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
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final FoodRepository foodRepository;

    @Autowired
    public CartService(
            CartRepository cartRepository, FoodRepository foodRepository) {
        this.cartRepository = cartRepository;
        this.foodRepository = foodRepository;
    }

    public Cart addToCart(String foodCode, String selectedToppings, Members members) {
        Food mainFood = foodRepository.getByFoodCode(foodCode);
        Food topping = foodRepository.getByFoodName(selectedToppings);

        List<Food> foodList = new ArrayList<>();
        foodList.add(mainFood);
        if (topping != null) {
            foodList.add(topping);
        }
        Cart cart = cartRepository.findCartByMemberIdx(members.getIdx());

        if (cart == null) {
            cart = new Cart();
            cart = Cart.builder()
                    .idx(cart.getIdx())
                    .code(cart.generateUniqueCode())
                    .payments("현금")
                    .foods(foodList)
                    .members(members)
                    .build();
        } else {
//            cart.getFoods().add(mainFood);
//            if (topping != null) {
//                cart.getFoods().add(topping);
            cart.setFoods(foodList);
        }
        log.info("idx"+ cart.getIdx());
        log.info("foodsList" + foodList.size());
        cartRepository.save(cart);
        return cart;
    }

    public Cart getCartByMemberIdx(Long memberIdx) {
        return cartRepository.findCartByMemberIdx(memberIdx);
    }

    public void clearCart(String memberId) {
        log.info("ClearCart check");
        cartRepository.clearCartByMemberId(memberId);
    }

}
