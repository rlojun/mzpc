package com.fivemin.mzpc.service.member;

import com.fivemin.mzpc.data.entity.Cart;
import com.fivemin.mzpc.data.entity.Food;
import com.fivemin.mzpc.data.entity.Members;
import com.fivemin.mzpc.data.repository.CartRepository;
import com.fivemin.mzpc.data.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

        List<Cart> cartList = cartRepository.findAllByMembersIdx(members.getIdx());

        Cart mainCart = new Cart();
        mainCart = Cart.builder()
                .idx(mainCart.getIdx())
                .code(mainCart.generateUniqueCode())
                .food(mainFood)
                .members(members)
                .store(members.getStore())
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
                    .store(members.getStore())
                    .build();
            cartList.add(toppingCart);
            cartRepository.save(toppingCart);
        }

        return cartList;
    }

    public List<Cart> getCartListByMemberIdx(Long memberIdx) {
        return cartRepository.findAllByMembersIdx(memberIdx);
    }

    public void removeFromCart(Long cartItemIdx) {
        cartRepository.removeFromCartByMemberIdx(cartItemIdx);
    }

    public void clearCart(Long memberIdx) {
        log.info("ClearCart check");
        cartRepository.clearCartByMemberIdx(memberIdx);
    }

}
