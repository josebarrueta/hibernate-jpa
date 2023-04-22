package org.oss.tx.listeners;

import jakarta.persistence.PrePersist;
import org.oss.tx.dao.AbstractTenantEntity;
import org.oss.tx.services.TenantContext;
import org.springframework.util.Assert;

public class TenantEntityListener {

    private static TenantContext tenantContext;

    @PrePersist
    public void setTenantId(AbstractTenantEntity entity) {
        Assert.notNull(tenantContext, "Cannot process this request because tenantContext is null");

        String tenantId = tenantContext.getTenantId();
        Assert.hasText(tenantId, "Can't save entity because the tenantId is null or empty.");

        entity.setTenantId(tenantId);
    }

    public void setTenantContext(TenantContext tenantContext) {
        TenantEntityListener.tenantContext = tenantContext;
    }
}
