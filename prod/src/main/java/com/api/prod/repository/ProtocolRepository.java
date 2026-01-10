package com.api.prod.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.prod.model.Protocol;

@Repository
public interface ProtocolRepository extends JpaRepository<Protocol, Long> {

    Optional<Protocol> findByProtocol(Long protocol);

    void deleteByProtocol(Long protocol);

    boolean existsByProtocol(Long protocol);

    @Modifying
    @Transactional
    @Query("""
        UPDATE Protocol p SET
            p.title = :title,
            p.description = :description,
            p.devDays = :devDays,
            p.supposedEnd = :supposedEnd,
            p.workload = :workload,
            p.savings = :savings
        WHERE p.protocol = :protocol
    """)
    int updateByProtocol(
        @Param("protocol") Long protocol,
        @Param("title") String title,
        @Param("description") String description,
        @Param("devDays") Integer devDays,
        @Param("supposedEnd") LocalDate supposedEnd,
        @Param("workload") Integer workload,
        @Param("savings") BigDecimal savings
    );
}
