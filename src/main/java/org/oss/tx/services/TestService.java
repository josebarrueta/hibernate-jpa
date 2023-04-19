package org.oss.tx.services;

import org.oss.tx.dao.Credentials;
import org.oss.tx.dao.TestEntity;
import org.oss.tx.repositories.TestEntityDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TestService {

    public final TestEntityDAO testEntityDAO;

    public TestService(TestEntityDAO testEntityDAO) {
        this.testEntityDAO = testEntityDAO;
    }


    @Transactional
    public TestEntity createEntity(String username, String password) {
        TestEntity testEntity = new TestEntity();

        testEntity.setCreated(LocalDateTime.now());

        Credentials credentials = new Credentials();
        credentials.setUser(username);
        credentials.setPassword(password);

        testEntity.setProperties(credentials);
        return testEntityDAO.save(testEntity);
    }

    public TestEntity findEntity(String entityId) {
        return testEntityDAO.findById(entityId);
    }
}
