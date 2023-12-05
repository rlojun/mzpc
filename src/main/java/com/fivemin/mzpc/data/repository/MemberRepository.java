package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Members, Long> {

    // 로그인 시 사용
    Members findById(String id);
    // 아이디 찾기 시 사용
    Members findByNameAndSsn(String name, String ssn);
    // 비밀번호 찾기 시 사용
    Members findByNameAndSsnAndEmail(String name, String ssn, String email);

    Optional<Members> findBySsn(String ssn);
}
