package org.oss.tx.repositories;

import org.oss.tx.dao.Tenant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends CrudRepository<Tenant, String> {
}
