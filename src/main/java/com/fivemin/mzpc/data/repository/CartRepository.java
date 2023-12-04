package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Cart;
import com.fivemin.mzpc.data.entity.Food;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {

    @Query("SELECT cart FROM Cart cart WHERE cart.members.idx = ?1")
    Cart findCartByMemberIdx(Long memberIdx);

    @Modifying
    @Query("DELETE FROM Cart c WHERE EXISTS (SELECT 1 FROM c.members m WHERE m.id = :memberId)")
    void clearCartByMemberId(String memberId);
}
