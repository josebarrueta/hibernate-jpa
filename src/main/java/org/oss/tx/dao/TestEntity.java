package org.oss.tx.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;
import org.oss.tx.encryption.EncryptedProperties;

import java.time.LocalDateTime;

@Table(name = "test_entity")
@Entity
public class TestEntity extends AbstractTenantEntity implements EncryptedProperties<Credentials> {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @GeneratedValue
    private String id;

    @Column(name = "created_at")
    private LocalDateTime created;

    @Column(name = "properties", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Credentials properties;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Override
    public Credentials getProperties() {
        return properties;
    }

    @Override
    public void setProperties(Credentials properties) {
        this.properties = properties;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        ACTIVE, SUSPENDED, DELETED
    }
}
