package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Members;
import com.fivemin.mzpc.data.entity.MileageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MileageInfoRepository extends JpaRepository<MileageInfo, Long> {

    List<MileageInfo> findByMembers(Members members);
}
