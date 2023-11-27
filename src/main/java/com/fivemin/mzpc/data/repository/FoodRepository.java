package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query("SELECT food FROM Food food WHERE food.category.idx=?1")
    List<Food> findByCategoryIdx(Long idx);


    @Query("SELECT food FROM Food food WHERE food.category.name = ?1")
    List<Food> findByCategoryIdx(String categoryName);

    @Query("SELECT food FROM Food food WHERE food.category.store.code = ?1 and food.topping=false ")
    List<Food> findByStorerCode(String storeCode);

    @Query("SELECT food FROM Food food WHERE food.category.code=?1 and food.code=?2")
    Food findByCode(String categoryCode, String foodCode);

}
