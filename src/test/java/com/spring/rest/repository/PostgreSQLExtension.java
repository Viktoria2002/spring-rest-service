package com.spring.rest.repository;

import com.spring.rest.configuration.PropertyManager;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;

import static com.spring.rest.configuration.Constants.DatabaseSetup.*;
import static com.spring.rest.configuration.Constants.TestDatabaseSetup.*;

public class PostgreSQLExtension implements BeforeAllCallback {
    protected static PostgreSQLContainer<?> POSTGRESQL_CONTAINER;

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        POSTGRESQL_CONTAINER = new PostgreSQLContainer<>(DOCKER_IMAGE_NAME)
                .withUsername(TEST_USERNAME)
                .withDatabaseName(DATABASE_NAME)
                .withPassword(TEST_PASSWORD)
                .withInitScript(INIT_TEST_DB_PATH);
        POSTGRESQL_CONTAINER.start();
        PropertyManager.setProperty(URL, POSTGRESQL_CONTAINER.getJdbcUrl());
        PropertyManager.setProperty(USERNAME, POSTGRESQL_CONTAINER.getUsername());
        PropertyManager.setProperty(PASSWORD, POSTGRESQL_CONTAINER.getPassword());
        PropertyManager.setProperty(DRIVER, POSTGRESQL_CONTAINER.getDriverClassName());
    }
}
