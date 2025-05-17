package com.example.messagelogger.service;

import com.example.messagelogger.model.Message;
import com.example.messagelogger.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository repo;

    public MessageService(MessageRepository repo) {
        this.repo = repo;
    }

    public Message save(Message message) {
        message.setTimestamp(LocalDateTime.now());
        return repo.save(message);
    }

    public List<Message> findAll() {
        return repo.findAll();
    }

    public List<Message> findBySender(String sender) {
        return repo.findBySender(sender);
    }

    public List<Message> findByDate(LocalDateTime start, LocalDateTime end) {
        return repo.findByTimestampBetween(start, end);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
