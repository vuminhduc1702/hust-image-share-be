package com.pinterestclonebackend.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class AbstractAuditingDate implements Serializable {
    @Column(name = "date_created", updatable = false)
    @CreatedDate
    private LocalDate dateCreated;

    @Column(name = "date_updated")
    @LastModifiedDate
    private LocalDate dateUpdated;
}
