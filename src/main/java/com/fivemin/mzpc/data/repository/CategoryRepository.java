package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
