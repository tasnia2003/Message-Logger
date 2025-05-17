package com.example.messagelogger.repository;

import com.example.messagelogger.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySender(String sender);
    List<Message> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}
