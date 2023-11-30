package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.dto.CartDto;
import com.fivemin.mzpc.data.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {
//    @Query("SELECT cart FROM Cart cart WHERE cart.members = ?1 ")
//    List<CartFoodDto> getCartByMemberCode(String memberCode);

    @Query("SELECT cart FROM Cart cart WHERE cart.members = ?1 ")
    List<CartDto> getCartByMemberIdx(Long memberIdx);
}
