package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.dto.TimeDto;
import com.fivemin.mzpc.data.entity.Store;
import com.fivemin.mzpc.data.entity.Times;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimesRepository extends JpaRepository<Times, Long> {
    List<Times> findByStore(Store store);
    Times findByCode(String code);
}
