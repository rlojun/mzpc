package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository <Chat, Long> {
}
