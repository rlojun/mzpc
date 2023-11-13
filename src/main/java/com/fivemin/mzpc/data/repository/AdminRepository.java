package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByAdminId(String adminId);
}
