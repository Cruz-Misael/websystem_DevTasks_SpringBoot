package com.api.prod.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "protocols")
public class Protocol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "protocol", nullable = false, unique = true)
    private Long protocol;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Lob
    @Column(
        name = "description",
        columnDefinition = "LONGTEXT",
        nullable = false
    )
    private String description;

    @Column(name = "dev_days")
    private Integer devDays;

    @Column(name = "supposed_end")
    private LocalDate supposedEnd;

    @Column(name = "workload")
    private Integer workload;

    @Column(name = "savings", precision = 15, scale = 2)
    private BigDecimal savings;

    @Column(
        name = "created_at",
        insertable = false,
        updatable = false
    )
    private LocalDateTime createdAt;

    // =========================
    // Getters and Setters
    // =========================

    public Long getId() {
        return id;
    }

    public Long getProtocol() {
        return protocol;
    }

    public void setProtocol(Long protocol) {
        this.protocol = protocol;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDevDays() {
        return devDays;
    }

    public void setDevDays(Integer devDays) {
        this.devDays = devDays;
    }

    public LocalDate getSupposedEnd() {
        return supposedEnd;
    }

    public void setSupposedEnd(LocalDate supposedEnd) {
        this.supposedEnd = supposedEnd;
    }

    public Integer getWorkload() {
        return workload;
    }

    public void setWorkload(Integer workload) {
        this.workload = workload;
    }

    public BigDecimal getSavings() {
        return savings;
    }

    public void setSavings(BigDecimal savings) {
        this.savings = savings;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
