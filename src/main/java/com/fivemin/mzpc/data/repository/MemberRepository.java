package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Members, Long> {

    // 로그인 시 사용
    Members findById(String id);
    // 아이디 찾기 시 사용
    Members findByNameAndSsn(String name, String ssn);
    Members findByNameAndSsnAndEmail(String name, String ssn, String email);
}
