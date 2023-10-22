package com.spring.rest.configuration;


import com.spring.rest.exception.LoadConfigFileException;

import java.io.InputStream;
import java.util.Properties;

import static com.spring.rest.configuration.Constants.DatabaseSetup.CONFIG_FILE;
import static com.spring.rest.configuration.Constants.ExceptionMessages.LOAD_CONFIG_FILE_EXCEPTION_MESSAGE;
import static com.spring.rest.configuration.Constants.ExceptionMessages.UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE;

public class PropertyManager {
    protected static final Properties PROPERTIES = new Properties();

    private PropertyManager() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE);
    }

    static {
        loadProperties(CONFIG_FILE);
    }

    public static String getProperty(String name){
        return PROPERTIES.getProperty(name);
    }

    public static void setProperty(String name, String value) {
        PROPERTIES.setProperty(name, value);
    }

    protected static void loadProperties(String configFile) {
        try (InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(configFile)) {
            PROPERTIES.load(inputStream);
        } catch (Exception e) {
            throw new LoadConfigFileException(LOAD_CONFIG_FILE_EXCEPTION_MESSAGE + e.getMessage());
        }
    }
}
