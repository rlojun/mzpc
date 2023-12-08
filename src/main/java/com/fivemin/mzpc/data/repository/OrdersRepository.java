package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT orders FROM Orders orders WHERE orders.store.code = ?1")
    List<Orders> findAllById(String storeCode);

    Orders findByCode(String orderCode);
}
