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
public class CategoryService {

    private final CategoryRepository categoryRepository;
//
    private final StoreRepository storeRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository, StoreRepository storeRepository){
        this.categoryRepository = categoryRepository;
        this.storeRepository = storeRepository;
    }

    public List<CategoryDto> getListCategory(String storeCode) {
        Store store = storeRepository.findByCode(storeCode);
        List<Category> category = categoryRepository.findByStoreIdx(store.getIdx());
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
}
