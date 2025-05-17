package com.example.messagelogger.controller;

import com.example.messagelogger.model.Message;
import com.example.messagelogger.service.MessageService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/export")
public class ExportController {
    private final MessageService service;

    public ExportController(MessageService service) {
        this.service = service;
    }

    @GetMapping("/zip")
    public ResponseEntity<InputStreamResource> exportZip() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        List<Message> messages = service.findAll();
        StringBuilder sb = new StringBuilder();
        for (Message m : messages) {
            sb.append("Sender: ").append(m.getSender())
              .append(", Time: ").append(m.getTimestamp())
              .append(", Message: ").append(m.getContent()).append("\n");
        }

        zos.putNextEntry(new ZipEntry("messages.txt"));
        zos.write(sb.toString().getBytes());
        zos.closeEntry();
        zos.close();

        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        InputStreamResource resource = new InputStreamResource(bis);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=messages.zip")
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }
}
