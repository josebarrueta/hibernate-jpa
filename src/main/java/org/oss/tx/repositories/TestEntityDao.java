package org.oss.tx.repositories;

import jakarta.persistence.EntityManager;
import org.oss.tx.dao.TestEntity;
import org.springframework.stereotype.Repository;

@Repository
public class TestEntityDao {

    private final EntityManager entityManager;

    public TestEntityDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public TestEntity save(TestEntity testEntity) {
        entityManager.persist(testEntity);
        return testEntity;
    }

    public TestEntity findById(String id) {
        return entityManager.find(TestEntity.class, id);
    }

}
