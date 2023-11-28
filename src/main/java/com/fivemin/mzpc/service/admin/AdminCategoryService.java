package com.fivemin.mzpc.service.admin;

import com.fivemin.mzpc.data.dto.CategoryDto;
import com.fivemin.mzpc.data.dto.StoreDto;
import com.fivemin.mzpc.data.entity.Category;
import com.fivemin.mzpc.data.entity.Store;
import com.fivemin.mzpc.data.repository.CategoryRepository;
import com.fivemin.mzpc.data.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AdminCategoryService {

    private final CategoryRepository categoryRepository;
//
    private final StoreRepository storeRepository;
    @Autowired
    public AdminCategoryService(CategoryRepository categoryRepository, StoreRepository storeRepository){
        this.categoryRepository = categoryRepository;
        this.storeRepository = storeRepository;
    }

    public List<CategoryDto> getListCategory(String storeCode) {
        Store store = storeRepository.findByCode(storeCode);
        List<Category> category = categoryRepository.findByStoreIdx(store.getIdx());
        List<CategoryDto> categoryList = new ArrayList<>();

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

    public StoreDto getStore(String storeCode) {
        Store store = storeRepository.findByCode(storeCode);

        StoreDto storeDto = StoreDto.builder()
                .idx(store.getIdx())
                .code(store.getCode())
                .name(store.getName())
                .build();

        return storeDto;
    }

    @Transactional
    public void addCategory(CategoryDto categoryDto, String storeCode) {
       Store store = storeRepository.findByCode(storeCode);

       Category category = Category.builder()
               .code(makeCode())
               .name(categoryDto.getName())
               .store(store)
               .build();

       categoryRepository.save(category);
    }

    private String makeCode(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'C'HHMMyyyymmddss");
        return currentDateTime.format(formatter);
    }

    public CategoryDto modifyCategoryForm(String categoryCode) {
        Category category = categoryRepository.findByCode(categoryCode);

        CategoryDto categoryDto = CategoryDto.builder()
                .idx(category.getIdx())
                .code(category.getCode())
                .name(category.getName())
                .build();

        return categoryDto;
    }

    @Transactional
    public void modifyCategory(CategoryDto categoryDto) {
        categoryRepository.updateCategoryNameByIdx(categoryDto.getIdx(),categoryDto.getName());
        log.info("name : {}", categoryDto.getName());
        log.info("idx : {} ", categoryDto.getIdx());

    }

    public void deleteCategory(Long categoryIdx) {
        categoryRepository.deleteById(categoryIdx);
    }


}