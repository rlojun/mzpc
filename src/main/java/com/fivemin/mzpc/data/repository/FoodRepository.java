package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
