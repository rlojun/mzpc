package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Category;
import com.fivemin.mzpc.data.entity.Food;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findByCategoryName(String category);


    @Query("SELECT food FROM Food food WHERE food.category.idx = ?1")
    List<Food> findByCategoryIdx(Long categoryIdx);

}
