package org.oss.tx.repositories;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.oss.tx.dao.AbstractTenantEntity;
import org.oss.tx.services.TenantContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("NullableProblems")
public class TenantAwareJpaRepository<T, ID> extends SimpleJpaRepository<T, ID> {

    private final EntityManager entityManager;

    public TenantAwareJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<T> findById(ID id) {
        if (isTenantEntity()) {
            enableTenantFilter();
        }
        return super.findById(id);
    }

    @Override
    public List<T> findAll() {
        if (isTenantEntity()) {
            enableTenantFilter();
        }
        return super.findAll();
    }

    @Override
    public Optional<T> findOne(Specification<T> spec) {
        if (isTenantEntity()) {
            enableTenantFilter();
        }
        return super.findOne(spec);
    }

    @Override
    public List<T> findAll(Specification<T> spec) {
        if (isTenantEntity()) {
            enableTenantFilter();
        }
        return super.findAll(spec);
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        if (isTenantEntity()) {
            enableTenantFilter();
        }
        return super.findAll(spec, pageable);
    }

    @Override
    public List<T> findAll(Specification<T> spec, Sort sort) {
        if (isTenantEntity()) {
            enableTenantFilter();
        }
        return super.findAll(spec, sort);
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        if (isTenantEntity()) {
            enableTenantFilter();
        }
        return super.findOne(example);
    }

    private boolean isTenantEntity() {
        Class<T> tClass = getDomainClass();
        return AbstractTenantEntity.class.isAssignableFrom(tClass);
    }

    private void enableTenantFilter() {
        Session session = entityManager.unwrap(Session.class);
        TenantContext tenantContext = TenantContext.getInstance();
        session.enableFilter("filterByTenantId").setParameter("tenantId", tenantContext.getTenantId());
    }
}
