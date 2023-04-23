package org.oss.tx.controllers;

import org.oss.tx.dao.Tenant;
import org.oss.tx.services.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tenants")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @PostMapping
    public Tenant createTenant(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        Assert.hasText(name, "Cannot create tenant without a name.");
        return tenantService.createTenant(name);
    }

    @GetMapping("/{id}")
    public Tenant getTenant(@PathVariable String id) {
        return tenantService.findEntity(id);
    }

    @GetMapping()
    public List<Tenant> findTenants() {
        return tenantService.findAll();
    }
}
