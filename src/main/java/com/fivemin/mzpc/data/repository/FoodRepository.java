package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Category;
import com.fivemin.mzpc.data.entity.Food;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query("SELECT food FROM Food food WHERE food.category.idx=?1")
    List<Food> findByCategoryIdx(Long idx);


    @Query("SELECT food FROM Food food WHERE food.category.name = ?1")
    List<Food> findByCategoryIdx(String categoryName);

}
