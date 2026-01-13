package com.api.prod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.api.prod.model.Protocol;
import com.api.prod.repository.ProtocolRepository;


@Service
public class ProtocolService {

    private final ProtocolRepository repository;
    private final RestTemplate restTemplate;

    @Value("${n8n.webhook.url}")
    private String n8nWebhookUrl;

    public ProtocolService(ProtocolRepository repository) {
        this.repository = repository;
        this.restTemplate = new RestTemplate();
    }

    // =========================
    // POST → n8n
    // =========================
    public void callN8n(Long protocol) {

        Protocol entity = getByProtocol(protocol);

        Map<String, Object> payload = new HashMap<>();
        payload.put("protocol", entity.getProtocol());
        payload.put("title", entity.getTitle());
        payload.put("description", entity.getDescription());
        payload.put("devDays", entity.getDevDays());
        payload.put("workload", entity.getWorkload());
        payload.put("savings", entity.getSavings());

        System.out.println("=== ENVIANDO AO N8N ===");
        System.out.println("URL: " + n8nWebhookUrl);
        System.out.println("PAYLOAD: " + payload);

        try {
            restTemplate.postForEntity(
                n8nWebhookUrl,
                payload,
                String.class
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }



    // =========================
    // GET por protocol
    // =========================
    public Protocol getByProtocol(Long protocol) {
        return repository.findByProtocol(protocol)
                .orElseThrow(() ->
                    new RuntimeException("Protocolo não encontrado")
                );
    }

    // =========================
    // UPDATE por protocol
    // =========================
    @Transactional
    public Protocol updateByProtocol(Long protocol, Protocol data) {

        Protocol entity = getByProtocol(protocol);

        if (data.getTitle() != null) {
            entity.setTitle(data.getTitle());
        }

        if (data.getDescription() != null) {
            entity.setDescription(data.getDescription());
        }

        if (data.getDevDays() != null) {
            entity.setDevDays(data.getDevDays());
        }

        if (data.getSupposedEnd() != null) {
            entity.setSupposedEnd(data.getSupposedEnd());
        }

        if (data.getWorkload() != null) {
            entity.setWorkload(data.getWorkload());
        }

        if (data.getSavings() != null) {
            entity.setSavings(data.getSavings());
        }

        return repository.save(entity);
    }

    
    public void deleteByProtocol(Long protocol) {
        Protocol entity = getByProtocol(protocol);
        repository.delete(entity);
    }

    public List<Protocol> getAll() {
        return repository.findAll();
    }
}