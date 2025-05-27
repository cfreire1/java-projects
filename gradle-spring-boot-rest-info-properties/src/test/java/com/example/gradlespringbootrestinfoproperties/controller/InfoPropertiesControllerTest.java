package com.example.gradlespringbootrestinfoproperties.controller;

import com.example.gradlespringbootrestinfoproperties.util.UtilProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class InfoPropertiesControllerTest {
    @Mock
    private UtilProperties utilProperties;

    @InjectMocks
    private InfoPropertiesController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("key1", "value1");
        properties.put("key2", "value2");

        when(utilProperties.getListProperties(anyString())).thenReturn(properties);

        ResponseEntity<Map<String, Object>> response = controller.getAllProperties("test.properties");

        // Verifica que el status code de la respuesta sea 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifica que el cuerpo de la respuesta contenga las propiedades esperadas
        assertEquals(properties, response.getBody());
    }

    @Test
    public void testFindProperties() {
        String key = "key1";
        String value = "value1";

        when(utilProperties.getPropertiesValue(anyString())).thenReturn(value);

        ResponseEntity<Map<String, Object>> response = controller.findProperties(key);

        // Verifica que el status code de la respuesta sea 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifica que el cuerpo de la respuesta contenga la propiedad esperada
        Map<String, Object> expected = new HashMap<>();
        expected.put(key, value);
        assertEquals(expected, response.getBody());
    }
}
