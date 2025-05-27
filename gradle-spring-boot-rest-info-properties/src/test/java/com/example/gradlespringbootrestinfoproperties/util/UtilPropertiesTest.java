package com.example.gradlespringbootrestinfoproperties.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

public class UtilPropertiesTest {
    private UtilProperties utilProperties;
    private ApplicationContext applicationContext;
    private Environment environment;
    private Resource resource;
    private PropertySource<?> propertySource;

    @BeforeEach
    public void setUp() {
        applicationContext = mock(ApplicationContext.class);
        environment = mock(Environment.class);
        resource = mock(Resource.class);
        propertySource = mock(PropertySource.class);
        utilProperties = new UtilProperties(applicationContext);
    }

    @Test
    public void testGetListProperties() throws Exception {
        //falta
    }

    @Test
    public void testGetPropertiesValue() {
        String key = "key1";
        when(applicationContext.getEnvironment()).thenReturn(environment);
        when(environment.getProperty(key)).thenReturn("value1");

        Object value = utilProperties.getPropertiesValue(key);

        assertEquals("value1", value);
    }
}
