package org.oss.tx.specifications;

import org.oss.tx.dao.TestEntity;
import org.springframework.data.jpa.domain.Specification;

public class TestEntitySpecifications {

    public static Specification<TestEntity> testEntityIsActive() {
        return (root, query, cb) -> cb.equal(root.get("status"), TestEntity.Status.ACTIVE);
    }
}
