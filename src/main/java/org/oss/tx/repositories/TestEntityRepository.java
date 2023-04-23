package org.oss.tx.repositories;

import org.oss.tx.dao.TestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestEntityRepository extends CrudRepository<TestEntity, String> {
}
