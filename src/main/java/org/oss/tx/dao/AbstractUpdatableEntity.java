package org.oss.tx.dao;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.oss.tx.listeners.UpdatableEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(UpdatableEntityListener.class)
public abstract class AbstractUpdatableEntity {

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime created;

    @Column(name = "updated_at")
    private LocalDateTime updated;

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
