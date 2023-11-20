package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findById(String id);
//    Admin findByIdAndPw(String adminId, String adminPw);

    Admin findByCode(String adminCode);

//    @Query("SELECT admin.idx FROM Admin admin WHERE admin.code = ?1")
//    Long findByCode(String code);


}
