package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query("SELECT category.idx  FROM Category category WHERE category.name=?1")
    Long findByName(String name);

    @Query("SELECT category FROM Category category WHERE category.store.name = ?1 ")
    List<Category> findByStoreName(String name);

    @Query("SELECT category FROM Category category WHERE category.store.idx = ?1 ")
    List<Category> findByStoreIdx(Long storeIdx);


}
