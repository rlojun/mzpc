package com.fivemin.mzpc.service.admin;

import com.fivemin.mzpc.data.dto.AdminDto;
import com.fivemin.mzpc.data.dto.CategoryDto;
import com.fivemin.mzpc.data.entity.Admin;
import com.fivemin.mzpc.data.entity.Category;
import com.fivemin.mzpc.data.repository.AdminRepository;
import com.fivemin.mzpc.data.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;

@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final AdminRepository adminRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository, AdminRepository adminRepository){
        this.categoryRepository = categoryRepository;
        this.adminRepository = adminRepository;
    }
    public List<CategoryDto> getListCategory(String adminCode) {
        Admin admin = adminRepository.findByCode(adminCode);
        List<Category> category = categoryRepository.findByAdminIdx(admin.getIdx());
        List<CategoryDto> categoryList = new ArrayList<>();
        AdminDto adminDto = AdminDto.builder()
                            .code(adminCode)
                            .build();

        for(Category categorys: category ){
            CategoryDto categoryDto = CategoryDto.builder()
                            .idx(categorys.getIdx())
                    .code(categorys.getCode())
                    .name(categorys.getName())
                    .adminDto(adminDto)
            .       build();
            categoryList.add(categoryDto);
        }

        log.info("cateogryList : {}", categoryList);
        return categoryList;
    }

//    public Category getCategoryIdx(String name) {
//
//        return categoryRepository.findByName(name);
//    }

//    @Transactional
    public void addCategory(String categoryName, String adminCode) {



    }

//    private String makeCode(){
//        LocalDateTime currentTime = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHMMyyyymmddss");
//        return null;
//    }
}
