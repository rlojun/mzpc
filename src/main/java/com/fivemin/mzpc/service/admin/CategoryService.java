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

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    public List<Category> getListCategory(String adminCode) {

        Admin admin = adminRepository.findByCode(adminCode);

        return categoryRepository.findByAdminIdx(admin.getIdx());
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
