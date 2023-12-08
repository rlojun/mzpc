package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT orders FROM Orders orders WHERE orders.store.code = ?1")
    List<Orders> findAllById(String storeCode);

    Orders findByCode(String code);

    @Modifying
    @Query("UPDATE Orders SET purchaseStatus=?2, cookComplete=?3 WHERE idx=?1")
    void modifyByCode(Long idx,boolean cookComplete,boolean purchaseStatus);

    @Query("SELECT o FROM Orders o WHERE o.members.idx = ?1")
    List<Orders> findOrdersByMemberIdx(Long memberIdx);
}
