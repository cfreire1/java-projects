package com.example.api.persistence;

import com.example.api.persistence.entity.Rss;
import com.example.api.persistence.xmlservice.ExampleXmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RssRepositoryImpl implements RssRepository {

    @Autowired
    public ExampleXmlService exampleXmlService;

    @Override
    public Rss getServiceXmlService() {
        return exampleXmlService.getServiceExample();
    }
}
