package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Members, Long> {

    Members findById(String id);
}
