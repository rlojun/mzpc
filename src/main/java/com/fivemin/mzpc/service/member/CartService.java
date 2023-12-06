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

    public List<Cart> addToCart(String foodCode, String selectedToppings, Members members) {
        Food mainFood = foodRepository.getByFoodCode(foodCode);
        Food topping = foodRepository.getByFoodName(selectedToppings);

        List<Cart> cartList = cartRepository.findCartListByMemberIdx(members.getIdx());

        Cart mainCart = new Cart();
        mainCart = Cart.builder()
                .idx(mainCart.getIdx())
                .code(mainCart.generateUniqueCode())
                .food(mainFood)
                .members(members)
                .build();
        cartList.add(mainCart);
        cartRepository.save(mainCart);

        if (topping != null) {
            Cart toppingCart = new Cart();
            toppingCart = Cart.builder()
                    .idx(toppingCart.getIdx())
                    .code(toppingCart.generateUniqueCode())
                    .food(topping)
                    .members(members)
                    .build();
            cartList.add(toppingCart);
            cartRepository.save(toppingCart);
        }

        return cartList;
    }

    public List<Cart> getCartListByMemberIdx(Long memberIdx) {
        return cartRepository.findCartListByMemberIdx(memberIdx);
    }

    public void clearCart(String memberId) {
        log.info("ClearCart check");
        cartRepository.clearCartByMemberId(memberId);
    }

}
