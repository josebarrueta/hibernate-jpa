package org.oss.tx;

import jakarta.persistence.EntityManagerFactory;
import org.oss.tx.filters.TenantContextFilter;
import org.oss.tx.listeners.TenantEntityListener;
import org.oss.tx.repositories.TenantAwareJpaRepository;
import org.oss.tx.services.TenantContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.TimeZone;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"org.oss.tx.repositories"}, repositoryBaseClass = TenantAwareJpaRepository.class, transactionManagerRef = "txManager")
public class DbTransactionApplication {

    static {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(DbTransactionApplication.class, args);
    }

    @Bean
    public JpaTransactionManager txManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public TenantContext tenantContext() {
        return new TenantContext();
    }

    @Bean
    public TenantEntityListener tenantEntityListener() {
        TenantEntityListener listener = new TenantEntityListener();
        listener.setTenantContext(tenantContext());
        return listener;
    }

    @Bean
    public FilterRegistrationBean<TenantContextFilter> tenantContextFilter() {
        TenantContextFilter tenantContextFilter = new TenantContextFilter(tenantContext());
        FilterRegistrationBean<TenantContextFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(tenantContextFilter);
        registrationBean.addUrlPatterns("/testEntities/*");
        return registrationBean;
    }
}
