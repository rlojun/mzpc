package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.dto.OrdersDto;
import com.fivemin.mzpc.data.entity.Cart;
import com.fivemin.mzpc.data.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT o FROM Orders o WHERE o.members.idx = ?1")
    List<Orders> findOrdersByMemberIdx(Long memberIdx);
}
