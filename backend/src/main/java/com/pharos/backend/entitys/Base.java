package com.pharos.backend.entitys;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Base {
    
    @CreatedDate
    @Column(name = "fechaCreacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
    
    @LastModifiedDate
    @Column(name = "fechaActualizacion")
    private LocalDateTime fechaActualizacion;

    @PrePersist
    private void prepersist(){
        setFechaCreacion(LocalDateTime.now());
    }

    @PreUpdate
    private void preupdate(){
        setFechaActualizacion(LocalDateTime.now());
    }
}
