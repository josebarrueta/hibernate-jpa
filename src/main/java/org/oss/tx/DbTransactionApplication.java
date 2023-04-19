package org.oss.tx;

import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"org.oss.tx.repositories"}, transactionManagerRef = "txManager")
public class DbTransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbTransactionApplication.class, args);
    }



    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Bean
    public HibernateTransactionManager txManager(SessionFactory sessionFactory, DataSource dataSource) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
