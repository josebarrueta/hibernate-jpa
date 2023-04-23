package org.oss.tx.services;

import org.oss.tx.dao.Credentials;
import org.oss.tx.dao.Tenant;
import org.oss.tx.dao.TestEntity;
import org.oss.tx.exception.ResourceNotFoundException;
import org.oss.tx.repositories.TenantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TenantService {

    private final TenantRepository tenantRepository;

    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Transactional
    public Tenant createTenant(String name) {
        Tenant tenant = new Tenant();
        tenant.setName(name);
        tenant.setStatus(Tenant.Status.ACTIVE);
        return tenantRepository.save(tenant);
    }

    public Tenant findEntity(String tenantId) {
        Optional<Tenant> optional = tenantRepository.findById(tenantId);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(Tenant.class, tenantId);
        }
        return optional.get();
    }

    public List<Tenant> findAll() {
        Iterable<Tenant> testEntities = tenantRepository.findAll();
        List<Tenant> result = new ArrayList<>();
        testEntities.forEach(result::add);
        return result;
    }

}
