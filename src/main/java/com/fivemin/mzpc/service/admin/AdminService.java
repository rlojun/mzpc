package com.fivemin.mzpc.service.admin;

import com.fivemin.mzpc.data.dto.AdminDto;
import com.fivemin.mzpc.data.entity.Admin;
import com.fivemin.mzpc.data.entity.Store;
import com.fivemin.mzpc.data.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminService {
    private final AdminRepository adminRepository;
    @Autowired
    public AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }


    public AdminDto getAdminName(Long storeIdx) {
        Admin admin = adminRepository.findByStoreIdx(storeIdx);

        AdminDto adminDto = AdminDto.builder()
                .name(admin.getName())
                .build();

        return adminDto;
    }
}
