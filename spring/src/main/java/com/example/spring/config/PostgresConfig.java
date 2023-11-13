package com.example.spring.config;

import com.example.spring.common.TransactionControlBag;
import com.example.spring.common.TransactionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.spring.repository.postgres",
        entityManagerFactoryRef = "postgresEntityManager",
        transactionManagerRef = "postgresTransactionManager"
)
public class PostgresConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.postgres.datasource")
    public DataSourceProperties postgresDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.postgres.datasource.hikari")
    public DataSource postgresDataSource() {
        return postgresDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Primary
    @Bean(name = "postgresEntityManager")
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(postgresDataSource())
                .packages("com.example.spring.entity.postgres")
                .build();
    }

    @Primary
    @Bean(name = "postgresTransactionManager")
    public PlatformTransactionManager postgresTransactionManager(@Qualifier("postgresEntityManager") EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }

    @Primary
    @Bean(name = "postgresTransactionService")
    public TransactionService postgresTransactionService(@Qualifier("postgresTransactionManager") PlatformTransactionManager platformTransactionManager,
                                                         TransactionControlBag controlBag) {
        return new TransactionService(platformTransactionManager, controlBag);
    }
}