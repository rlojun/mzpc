package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {

    Session findByCodeAndName(String code, String name);
    void deleteByCode(String code);
}
