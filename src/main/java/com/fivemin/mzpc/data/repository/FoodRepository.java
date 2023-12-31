package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Food;
import com.fivemin.mzpc.data.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query("SELECT food FROM Food food WHERE food.category.idx=?1")
    List<Food> findByCategoryIdx(Long idx);

    @Query("SELECT food FROM Food food WHERE food.category.name = ?1")
    List<Food> findByCategoryName(String categoryName);

    @Query("SELECT food FROM Food food WHERE food.category.store.name = ?1 ")
    List<Food> findByStoreName(String storeName);

    @Query("SELECT food FROM Food food WHERE food.idx = ?1 ")
    Food getByFoodIdx(Long foodIdx);
    @Query("SELECT food FROM Food food WHERE food.code = ?1 ")
    Food getByFoodCode(String foodCode);

    @Query("SELECT food FROM Food food WHERE food.name = ?1 ")
    Food getByFoodName(String foodName);

    @Query("SELECT food FROM Food food WHERE food.category.name = ?1 and food.topping = true")
    List<Food> findToppingByCategoryName(String categoryName, boolean isTopping);

    @Query("SELECT food FROM Food food WHERE food.category.store.code = ?1 and food.topping=?2 ")
    List<Food> findByStoreCode(String storeCode,boolean topping);

    @Query("SELECT food FROM Food food WHERE food.category.code=?1 and food.code=?2")
    Food findByCode(String categoryCode, String foodCode);

    Food findByIdx(Long foodIdx);
}
