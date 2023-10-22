package com.spring.rest.configuration;

import static com.spring.rest.configuration.Constants.ExceptionMessages.UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE;

public final class Constants {
    private Constants() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE);
    }

    public static final class ExceptionMessages {
        public static final String ORDER_NOT_FOUND_EXCEPTION_MESSAGE = "Order with ID = %d was not found";
        public static final String PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE = "Product with ID = %d was not found";
        public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "User with ID = %d was not found";
        public static final String UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE = "This is a utility class and cannot be instantiated";
        public static final String LOAD_CONFIG_FILE_EXCEPTION_MESSAGE = "Error loading configuration file: ";

        private ExceptionMessages() {
            throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE);
        }
    }

    public static final class Messages {
        public static final String PRODUCT_DELETED_MESSAGE = "Product with ID = %d was deleted";
        public static final String ORDER_DELETED_MESSAGE = "Order with ID = %d was deleted";
        public static final String USER_DELETED_MESSAGE = "User with ID = %d was deleted";
        private Messages() {
            throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE);
        }
    }

    public static final class DatabaseSetup {
        public static final String URL = "datasource.url";
        public static final String USERNAME = "datasource.username";
        public static final String PASSWORD = "datasource.password";
        public static final String DRIVER = "datasource.driver";
        public static final String HBM2DDL = "hibernate.hbm2ddl.auto";
        public static final String DIALECT = "hibernate.dialect";
        public static final String SCAN_PACKAGE = "com/spring/rest/model";
        public static final String CONFIG_FILE = "application.yml";

        private DatabaseSetup() {
            throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE);
        }
    }

    public static final class TestDatabaseSetup {
        public static final String DOCKER_IMAGE_NAME = "postgres:latest";
        public static final String DATABASE_NAME = "shop";
        public static final String TEST_USERNAME = "user";
        public static final String TEST_PASSWORD = "pass";
        public static final String INIT_TEST_DB_PATH = "db/inserts.sql";

        private TestDatabaseSetup() {
            throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE);
        }
    }
}
