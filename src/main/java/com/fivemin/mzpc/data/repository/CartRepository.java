package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Cart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CartRepository extends JpaRepository<Cart,Integer> {

    List<Cart> findAllByMembersIdx(Long memberIdx);

    List<Cart> findAllByMembersIdxAndOrderCompleteIsFalse(Long memberIdx);


    @Modifying
    @Query("DELETE FROM Cart c WHERE EXISTS (SELECT 1 FROM c.members m WHERE m.idx = :memberIdx) AND c.orderComplete = false")
    void clearCartByMemberIdx(Long memberIdx);

    @Modifying
    @Query("DELETE FROM Cart c WHERE c.idx = :cartItemIdx")
    void removeFromCartByMemberIdx(Long cartItemIdx);

    @Query("SELECT c FROM Cart c WHERE c.orders.idx=:idx")
    List<Cart> findByOrdersIdx(Long idx);

    @Query("SELECT c FROM Cart c WHERE c.store.code=?1 and c.orderComplete=true")
    List<Cart> findAllByStoreIdx(String storeCode);
}
