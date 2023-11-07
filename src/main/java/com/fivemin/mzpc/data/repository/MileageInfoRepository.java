package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.MileageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MileageInfoRepository extends JpaRepository<MileageInfo,Long> {
}
