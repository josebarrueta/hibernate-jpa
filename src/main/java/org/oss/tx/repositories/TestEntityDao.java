package org.oss.tx.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.oss.tx.dao.TestEntity;
import org.oss.tx.exception.ResourceNotFoundException;
import org.oss.tx.services.TenantContext;
import org.springframework.stereotype.Repository;

@Repository
public class TestEntityDao {

    private final EntityManager entityManager;

    private final TenantContext tenantContext;

    public TestEntityDao(EntityManager entityManager, TenantContext tenantContext) {
        this.entityManager = entityManager;
        this.tenantContext = tenantContext;
    }

    public TestEntity save(TestEntity testEntity) {
        entityManager.persist(testEntity);
        return testEntity;
    }

    public TestEntity findById(String id) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TestEntity> criteria = builder.createQuery(TestEntity.class);

        Root<TestEntity> root = criteria.from(TestEntity.class);
        criteria.select(root).where(builder.equal(root.get("id"), id),
                builder.equal(root.get("tenantId"), tenantContext.getTenantId()));

        try {
            return entityManager.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            throw new ResourceNotFoundException(TestEntity.class, id);
        }
    }

}
