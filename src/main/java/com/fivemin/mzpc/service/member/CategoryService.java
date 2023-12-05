package com.fivemin.mzpc.service.member;

import com.fivemin.mzpc.data.dto.CategoryDto;
import com.fivemin.mzpc.data.dto.StoreDto;
import com.fivemin.mzpc.data.entity.Category;
import com.fivemin.mzpc.data.entity.Store;
import com.fivemin.mzpc.data.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("memberCategoryService")
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, StoreRepository storeRepository) {
        this.categoryRepository = categoryRepository;
        this.storeRepository = storeRepository;
    }

//    public List<Category> getAllCategories() {
//        log.info("Category Service: getAllCategories");
//        return categoryRepository.findAll();
//    }
    public List<CategoryDto> getListCategory(String storeName) {
        log.info("Category Service: {}", storeName);
        Store store = storeRepository.findByName(storeName);
        List<Category> category = categoryRepository.findByStoreName(store.getName());
        List<CategoryDto> categoryList = new ArrayList<>();

        StoreDto storeDto = StoreDto.builder()
                .code(store.getCode())
                .build();

        for(Category categories: category) {
            CategoryDto categoryDto = CategoryDto.builder()
                    .idx(categories.getIdx())
                    .code(categories.getCode())
                    .name(categories.getName())
                    .build();
            categoryList.add(categoryDto);
        }

        return categoryList;
    }

}