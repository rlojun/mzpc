package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Members;
import com.fivemin.mzpc.data.entity.TimePurchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimePurchaseRepository extends JpaRepository<TimePurchase, Long> {

    List<TimePurchase> findByMembers(Members members);
}
