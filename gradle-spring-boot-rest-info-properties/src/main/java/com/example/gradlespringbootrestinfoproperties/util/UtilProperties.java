package com.example.gradlespringbootrestinfoproperties.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

@Component
public class UtilProperties {

    private static final String TEXT_CLASSPATH = "classpath:";
    private ApplicationContext context;

    @Autowired
    public UtilProperties(ApplicationContext context) {
        this.context = context;
    }

    /**
     * Código carga un archivo YAML utilizando Spring Framework y lo convierte en un objeto Properties.
     * Luego, crea un PropertiesPropertySource a partir de este objeto, lo que permite que las propiedades
     * del archivo YAML se utilicen como valores de propiedad en la aplicación.
     * @param archiveProp
     * @return Retorna el listado de propiedades con sus valores
     */
    public Map<String, Object> getListProperties(String archiveProp){

        Resource resource = context.getResource(TEXT_CLASSPATH+archiveProp);
        EncodedResource encodedResource = new EncodedResource(resource, StandardCharsets.UTF_8);

        YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
        factoryBean.setResources(encodedResource.getResource());
        Properties properties = factoryBean.getObject();

        PropertiesPropertySource propertySource = new PropertiesPropertySource(
                Objects.requireNonNull(encodedResource.getResource().getFilename()), properties);

        return propertySource.getSource();
    }

    /**
     * Obtiene el valor de una propiedad
     * @param key
     * @return
     */
    public Object getPropertiesValue (String key){
        return context.getEnvironment().getProperty(key);
    }
}
