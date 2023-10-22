package com.spring.rest.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

import static com.spring.rest.configuration.Constants.DatabaseSetup.*;
import static org.junit.jupiter.api.Assertions.*;

class MyConfigTest {
    @Test
    void testDataSource() {
        MyConfig myConfig = new MyConfig();
        DataSource dataSource = myConfig.dataSource();

        assertNotNull(dataSource);
        assertTrue(dataSource instanceof DriverManagerDataSource);

        String url = ((DriverManagerDataSource) dataSource).getUrl();
        String username = ((DriverManagerDataSource) dataSource).getUsername();
        String password = ((DriverManagerDataSource) dataSource).getPassword();

        assertEquals(PropertyManager.getProperty(URL), url);
        assertEquals(PropertyManager.getProperty(USERNAME), username);
        assertEquals(PropertyManager.getProperty(PASSWORD), password);
    }

    @Test
    void testEntityManagerFactory() {
        MyConfig myConfig = new MyConfig();
        LocalContainerEntityManagerFactoryBean entityManager = myConfig.entityManagerFactory();

        assertNotNull(entityManager);

        DataSource dataSource = entityManager.getDataSource();
        assertNotNull(dataSource);
        assertTrue(dataSource instanceof DriverManagerDataSource);

        JpaVendorAdapter vendorAdapter = entityManager.getJpaVendorAdapter();
        assertNotNull(vendorAdapter);
        assertTrue(vendorAdapter instanceof HibernateJpaVendorAdapter);
    }

    @Test
    void testTransactionManager() {
        MyConfig myConfig = new MyConfig();
        PlatformTransactionManager transactionManager = myConfig.transactionManager();

        assertNotNull(transactionManager);
        assertTrue(transactionManager instanceof JpaTransactionManager);
    }

    @Test
    public void testHibernateProperties() {
        MyConfig myConfig = new MyConfig();
        Properties properties = myConfig.hibernateProperties();

        assertNotNull(properties);
        assertEquals(PropertyManager.getProperty(DIALECT), properties.getProperty(DIALECT));
        assertEquals(PropertyManager.getProperty(HBM2DDL), properties.getProperty(HBM2DDL));
    }
}