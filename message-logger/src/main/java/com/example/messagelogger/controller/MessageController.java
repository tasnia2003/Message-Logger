package com.example.messagelogger.controller;

import com.example.messagelogger.model.Message;
import com.example.messagelogger.service.MessageService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        return service.save(message);
    }

    @GetMapping
    public List<Message> getAll() {
        return service.findAll();
    }

    @GetMapping("/sender/{sender}")
    public List<Message> getBySender(@PathVariable String sender) {
        return service.findBySender(sender);
    }

    @GetMapping("/date")
    public List<Message> getByDateRange(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end
    ) {
        return service.findByDate(start, end);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
