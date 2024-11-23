package com.jc.curso.springboot.jpa.springbootjpa.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Embeddable
public class Audit {

    @Column(name = "create_at")
    private LocalDateTime creatAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        System.out.println("evento del ciclo de vida del entity pre-persist");
        this.creatAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        System.out.println("evento del ciclo de vida del entity pre-update");
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(LocalDateTime creatAt) {
        this.creatAt = creatAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
