package org.oss.tx.dao;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;
import org.oss.tx.listeners.TenantEntityListener;

@FilterDef(name = "filterByTenantId", parameters = @ParamDef(name = "tenantId", type = String.class))
@Filters({
        @Filter(name = "filterByTenantId", condition = "tenant_id = :tenantId")
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
