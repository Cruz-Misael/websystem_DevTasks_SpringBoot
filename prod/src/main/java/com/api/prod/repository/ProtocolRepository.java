package com.api.prod.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.prod.model.Protocol;

@Repository
public interface ProtocolRepository extends JpaRepository<Protocol, Long> {

    Optional<Protocol> findByProtocol(Long protocol);

    void deleteByProtocol(Long protocol);

    boolean existsByProtocol(Long protocol);
}

