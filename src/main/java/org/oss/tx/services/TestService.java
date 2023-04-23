package org.oss.tx.services;

import org.oss.tx.dao.Credentials;
import org.oss.tx.dao.TestEntity;
import org.oss.tx.exception.ResourceNotFoundException;
import org.oss.tx.repositories.TestEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    public final TestEntityRepository testEntityRepository;

    public TestService(TestEntityRepository testEntityRepository) {
        this.testEntityRepository = testEntityRepository;
    }

    @Transactional
    public TestEntity createEntity(String username, String password) {
        TestEntity testEntity = new TestEntity();

        Credentials credentials = new Credentials();
        credentials.setUser(username);
        credentials.setPassword(password);

        testEntity.setProperties(credentials);
        return testEntityRepository.save(testEntity);
    }

    public TestEntity findEntity(String entityId) {
        Optional<TestEntity> testEntity = testEntityRepository.findById(entityId);
        if (testEntity.isEmpty()) {
            throw new ResourceNotFoundException(TestEntity.class, entityId);
        }
        return testEntity.get();
    }

    public List<TestEntity> findAll() {
        Iterable<TestEntity> testEntities = testEntityRepository.findAll();
        List<TestEntity> result = new ArrayList<>();
        testEntities.forEach(result::add);
        return result;
    }
}
