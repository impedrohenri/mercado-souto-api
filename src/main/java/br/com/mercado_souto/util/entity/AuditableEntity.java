package br.com.mercado_souto.util.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass 
@EntityListeners(AuditingEntityListener.class)
public class AuditableEntity extends BaseEntity {
    @JsonIgnore 
    @Version
    private Long version;

    @JsonIgnore
    @CreatedDate
    private LocalDate creationDate;

    @JsonIgnore
    @LastModifiedDate
    private LocalDate lastModifiedDate;
}
