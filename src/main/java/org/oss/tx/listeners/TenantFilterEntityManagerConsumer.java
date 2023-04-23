package org.oss.tx.listeners;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.oss.tx.services.TenantContext;

import java.util.function.Consumer;

public class TenantFilterEntityManagerConsumer implements Consumer<EntityManager> {

    private final TenantContext tenantContext;

    public TenantFilterEntityManagerConsumer(TenantContext tenantContext) {
        this.tenantContext = tenantContext;
    }

    @Override
    public void accept(EntityManager entityManager) {
        if (tenantContext.getTenantId() != null) {
            Session session = entityManager.unwrap(Session.class);
            session.enableFilter("filterByTenantId").setParameter("tenantId", tenantContext.getTenantId());
        }
    }
}
