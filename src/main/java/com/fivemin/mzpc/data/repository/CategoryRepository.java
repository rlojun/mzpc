package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query("SELECT category FROM Category category WHERE category.name=?1")
    Category findByName(String name);

    @Query("SELECT category FROM Category category WHERE category.store.name = ?1 ")
    List<Category> findByStoreName(String name);

    @Query("SELECT category FROM Category category WHERE category.store.idx = ?1 ")
    List<Category> findByStoreIdx(Long storeIdx);

    @Modifying
    @Transactional
    @Query("UPDATE Category category SET category.name = :name WHERE category.idx = :idx")
    void updateCategoryNameByIdx(Long idx, String name);

    Category findByCode(String categoryCode);

}
