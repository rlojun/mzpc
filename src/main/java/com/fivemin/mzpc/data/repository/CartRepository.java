package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Integer> {
}
