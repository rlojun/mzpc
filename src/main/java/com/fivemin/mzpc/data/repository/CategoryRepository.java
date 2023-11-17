package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.dto.CategoryDto;
import com.fivemin.mzpc.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query("SELECT category FROM Category category where category.admin.idx = :adminIdx")
    List<Category> findByAdminIdx(Long adminIdx);

    Category findByName(String name);



}
