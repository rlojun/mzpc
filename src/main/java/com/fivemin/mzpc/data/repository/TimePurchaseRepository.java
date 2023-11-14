package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.TimePurchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimePurchaseRepository extends JpaRepository<TimePurchase, Long> {
}
