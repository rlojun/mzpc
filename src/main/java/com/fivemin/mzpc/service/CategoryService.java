package com.fivemin.mzpc.service;

import com.fivemin.mzpc.data.entity.Category;
import com.fivemin.mzpc.data.entity.Food;
import com.fivemin.mzpc.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final FoodRepository foodRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(
            FoodRepository foodRepository,
            CategoryRepository categoryRepository) {
        this.foodRepository = foodRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}