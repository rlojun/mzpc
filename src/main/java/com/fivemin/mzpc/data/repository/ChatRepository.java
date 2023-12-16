package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository <ChatMessage, Long> {
}
