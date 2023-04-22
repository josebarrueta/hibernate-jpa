package org.oss.tx;

import jakarta.persistence.EntityManagerFactory;
import org.oss.tx.filters.TenantContextFilter;
import org.oss.tx.listeners.TenantEntityListener;
import org.oss.tx.listeners.TenantFilterEntityManagerConsumer;
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
@EnableJpaRepositories(basePackages = {"org.oss.tx.repositories"}, transactionManagerRef = "txManager")
public class DbTransactionApplication {

    static {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(DbTransactionApplication.class, args);
    }

    @Bean
    public JpaTransactionManager txManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory);
        transactionManager.setEntityManagerInitializer(tenantFilterEntityManagerConsumer());
        return transactionManager;
    }

    @Bean
    public TenantFilterEntityManagerConsumer tenantFilterEntityManagerConsumer() {
        return new TenantFilterEntityManagerConsumer(tenantContext());
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
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
