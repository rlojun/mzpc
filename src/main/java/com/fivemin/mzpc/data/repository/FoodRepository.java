package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findByCategoryName(String category);


}
