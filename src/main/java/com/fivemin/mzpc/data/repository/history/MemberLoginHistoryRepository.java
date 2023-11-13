package com.fivemin.mzpc.data.repository.history;

import com.fivemin.mzpc.data.entity.history.MemberLoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLoginHistoryRepository extends JpaRepository<MemberLoginHistory, Long> {
}
