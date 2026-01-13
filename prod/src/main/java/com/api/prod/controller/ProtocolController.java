package com.api.prod.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.prod.model.Protocol;
import com.api.prod.service.ProtocolService;

@RestController
@RequestMapping("/api/protocols")
public class ProtocolController {

    private final ProtocolService service;

    public ProtocolController(ProtocolService service) {
        this.service = service;
    }

    // =========================
    // GET específico (para IA)
    // =========================
    @GetMapping("/{protocol}")
    public ResponseEntity<Protocol> getOne(
            @PathVariable Long protocol) {

        return ResponseEntity.ok(
            service.getByProtocol(protocol)
        );
    }

    // =========================
    // UPDATE completo
    // =========================
    @PutMapping("/{protocol}")
    public ResponseEntity<Protocol> update(
            @PathVariable Long protocol,
            @RequestBody Protocol body) {

        return ResponseEntity.ok(
            service.updateByProtocol(protocol, body)
        );
    }

    // =========================
    // POST → n8n
    // =========================
    @PostMapping("/{protocol}/analyze")
    public ResponseEntity<Map<String, Object>> analyzeProtocol(
            @PathVariable Long protocol) {

        service.callN8n(protocol);

        return ResponseEntity.ok(
            Map.of(
                "status", "sent",
                "protocol", protocol
            )
        );
    }
    
    @DeleteMapping("/{protocol}")
    public ResponseEntity<?> delete(@PathVariable Long protocol) {

        service.deleteByProtocol(protocol);

        return ResponseEntity.ok(
            Map.of("status", "deleted", "protocol", protocol)
        );
    }

    @GetMapping
    public ResponseEntity<List<Protocol>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

}
