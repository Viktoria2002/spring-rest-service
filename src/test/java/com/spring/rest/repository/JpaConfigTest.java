package com.spring.rest.repository;

import com.spring.rest.configuration.PropertyManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

import static com.spring.rest.configuration.Constants.DatabaseSetup.*;

@Configuration
@ComponentScan(basePackages = "com.spring.rest.repository")
@EnableJpaRepositories("com.spring.rest.repository")
@EnableTransactionManagement
public class JpaConfigTest {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(PropertyManager.getProperty(DRIVER));
        dataSource.setUrl(PropertyManager.getProperty(URL));
        dataSource.setUsername(PropertyManager.getProperty(USERNAME));
        dataSource.setPassword(PropertyManager.getProperty(PASSWORD));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());
        entityManager.setPackagesToScan(SCAN_PACKAGE);
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(vendorAdapter);
        entityManager.setJpaProperties(hibernateProperties());
        return entityManager;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty(DIALECT, PropertyManager.getProperty(DIALECT));
        properties.setProperty(HBM2DDL, PropertyManager.getProperty(HBM2DDL));
        return properties;
    }
}
