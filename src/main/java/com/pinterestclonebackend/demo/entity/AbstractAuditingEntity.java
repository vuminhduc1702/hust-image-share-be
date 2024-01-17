package com.pinterestclonebackend.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class AbstractAuditingEntity extends AbstractAuditingDate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "created_by", updatable = false)
    @CreatedBy
    @JsonIgnore
    private Long createdBy;
}
