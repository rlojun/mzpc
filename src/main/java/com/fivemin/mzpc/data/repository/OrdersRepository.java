package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    Orders findByCode(String code);

    @Query("SELECT DISTINCT o FROM Orders o JOIN o.carts c WHERE c.members.idx = ?1")
    List<Orders> findOrdersByMemberIdx(Long memberIdx);

    @Query("SELECT orders FROM Orders orders WHERE orders.cookComplete = false and orders.store.code =?1 order by orders.createdAt")
    List<Orders> findAllCookIncompleteByStoreCode(String storeCode);

    @Query("SELECT o FROM Orders o WHERE o.store.code=?1 and o.cookComplete=false")
    List<Orders> findAllByStoreCode(String storeCode);

}
