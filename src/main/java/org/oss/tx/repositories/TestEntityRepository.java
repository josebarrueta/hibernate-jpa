package org.oss.tx.repositories;

import org.oss.tx.dao.TestEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestEntityRepository extends CrudRepository<TestEntity, String>, JpaSpecificationExecutor<TestEntity> {

    interface Specs {
        static Specification<TestEntity> testEntityIsActive() {
            return (root, query, cb) -> cb.equal(root.get("status"), TestEntity.Status.ACTIVE);
        }

        static Specification<TestEntity> testEntityByIdAndStatus(final String id, final TestEntity.Status status) {
            return ((root, query, builder) -> builder.and(builder.equal(root.get("id"), id), builder.equal(root.get("status"), status)));
        }
    }
}
