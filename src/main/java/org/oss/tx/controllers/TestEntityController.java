package org.oss.tx.controllers;

import org.oss.tx.dao.TestEntity;
import org.oss.tx.services.TestService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestEntityController {

    private final TestService testService;

    public TestEntityController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/{id}")
    public TestEntity getTestEntity(@PathVariable String id) {
        return null;
    }

    @PostMapping
    public TestEntity createTestEntity(@RequestBody Map<String, String> properties) {

        String username = properties.get("username");
        String password = properties.get("password");

        Assert.hasText(username, "Username cannot be null or empty.");
        Assert.hasText(password, "password cannot be null or empty.");

        return testService.createEntity(username, password);
    }
}
