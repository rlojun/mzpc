package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.dto.CategoryDto;
import com.fivemin.mzpc.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query("SELECT category.idx  FROM Category category WHERE category.name=?1")
    Long findByName(String name);

    @Query("SELECT category FROM Category category WHERE category.store.idx = ?1 ")
    List<Category> findByStoreIdx(Long storeIdx);

    Category findByIdx(Long categoryIdx);

    @Modifying
    @Transactional
    @Query("UPDATE Category category SET category.name = :name WHERE category.idx = :idx")
    void updateCategoryNameByIdx(Long idx, String name);
}
