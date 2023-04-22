package org.oss.tx.services;

import org.springframework.util.Assert;

public class TenantContext {

    private static final ThreadLocal<String> TENANT_ID_HOLDER = new ThreadLocal<>();

    public void setTenantId(String tenantId) {
        Assert.hasText(tenantId, "tenantId cannot be null or empty.");
        TENANT_ID_HOLDER.set(tenantId);
    }

    public String getTenantId() {
        return TENANT_ID_HOLDER.get();
    }

    public void clearContext() {
        TENANT_ID_HOLDER.remove();
    }
}
