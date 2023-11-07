package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.OnlineMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnlineMemberRepository extends JpaRepository<OnlineMember, Long> {
}
