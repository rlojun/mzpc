package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findByName(String name);
}
