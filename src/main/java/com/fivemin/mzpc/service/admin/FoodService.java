//package com.fivemin.mzpc.service.admin;
//
//import com.fivemin.mzpc.data.entity.Admin;
//import com.fivemin.mzpc.data.entity.Category;
//import com.fivemin.mzpc.data.entity.Food;
//import com.fivemin.mzpc.data.repository.AdminRepository;
//import com.fivemin.mzpc.data.repository.CategoryRepository;
//import com.fivemin.mzpc.data.repository.FoodRepository;
//import lombok.extern.java.Log;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@Slf4j
//public class FoodService {
//
//    @Autowired
//    private FoodRepository foodRepository;
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    public List<Food> getListFoodByName(String name) {
//        Category category = categoryRepository.findByName(name);
//
//        return foodRepository.findByCategoryIdx(category.getIdx());
//    }
//
//}
