package com.spring.rest.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@EnableWebMvc
public class MyWebInitializerTest {

    @Test
    public void testGetRootConfigClasses() {
        MyWebInitializer initializer = new MyWebInitializer();
        Class<?>[] rootConfigClasses = initializer.getRootConfigClasses();

        assertNotNull(rootConfigClasses);
        assertEquals(0, rootConfigClasses.length);
    }

    @Test
    public void testGetServletConfigClasses() {
        MyWebInitializer initializer = new MyWebInitializer();
        Class<?>[] servletConfigClasses = initializer.getServletConfigClasses();

        assertNotNull(servletConfigClasses);
        assertEquals(1, servletConfigClasses.length);
        assertEquals(MyConfig.class, servletConfigClasses[0]);
    }

    @Test
    public void testGetServletMappings() {
        MyWebInitializer initializer = new MyWebInitializer();
        String[] servletMappings = initializer.getServletMappings();

        assertNotNull(servletMappings);
        assertEquals(1, servletMappings.length);
        assertEquals("/", servletMappings[0]);
    }
}
