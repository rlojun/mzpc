package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findById(String id);

    @Query("SELECT admin FROM Admin admin WHERE admin.store.idx = ?1 ")
    Admin findByStoreIdx(Long storeIdx);

}
