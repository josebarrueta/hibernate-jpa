package org.oss.tx.dao;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;
import org.oss.tx.listeners.TenantEntityListener;

@Filters({
        @Filter(name = "filterByTenantId", condition = "tenant_id = :tenant_id")
})
@MappedSuperclass
@EntityListeners(TenantEntityListener.class)
public abstract class AbstractTenantEntity extends AbstractUpdatableEntity {

    @Column(name = "tenant_id")
    private String tenantId;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
