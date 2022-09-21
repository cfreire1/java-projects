package com.example.api.persistence.xmlservice;

import com.example.api.persistence.entity.Rss;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class ExampleXmlService {
    private final String HOST_SERVICE_XML ="https://www.feedforall.com/sample.xml";

    public Rss getServiceExample(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate
                .exchange(HOST_SERVICE_XML, HttpMethod.GET, new HttpEntity<>(getHeaders()), Rss.class)
                .getBody();
    }
    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
        return headers;
    }

}
