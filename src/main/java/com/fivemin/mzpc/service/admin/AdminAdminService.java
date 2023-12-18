package com.fivemin.mzpc.service.admin;

import com.fivemin.mzpc.data.dto.AdminDto;
import com.fivemin.mzpc.data.entity.Admin;
import com.fivemin.mzpc.data.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminAdminService {
    private final AdminRepository adminRepository;
    @Autowired
    public AdminAdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }


    public AdminDto getAdminName(Long storeIdx) {
        Admin admin = adminRepository.findByStoreIdx(storeIdx);

        if (admin != null) {
            AdminDto adminDto = AdminDto.builder()
                    .name(admin.getName())
                    .build();
            return adminDto;
        } else {
            System.out.println("관리자 목록이 없습니다.");
            return null;
        }
    }

}
