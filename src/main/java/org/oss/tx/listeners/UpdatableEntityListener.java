package org.oss.tx.listeners;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.oss.tx.dao.AbstractUpdatableEntity;

import java.time.LocalDateTime;

public class UpdatableEntityListener {

    @PreUpdate
    @PrePersist
    public void setLastUpdate(AbstractUpdatableEntity entity) {
        entity.setUpdated(LocalDateTime.now());
    }
}
