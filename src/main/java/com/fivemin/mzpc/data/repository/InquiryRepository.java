package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, String> {
    List<Inquiry> findByMemberId(String memberId);
    List<Inquiry> findByAdminId(String adminId);
    List<Inquiry> findByAdminIdIsNull();
}