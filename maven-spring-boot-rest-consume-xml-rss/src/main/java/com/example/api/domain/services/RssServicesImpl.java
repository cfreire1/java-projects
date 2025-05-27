package com.example.api.domain.services;

import com.example.api.domain.dto.ItemDTO;
import com.example.api.domain.dto.RssDTO;
import com.example.api.domain.mapper.RssMapper;
import com.example.api.persistence.RssRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RssServicesImpl implements RssServices {

    @Autowired
    private RssRepository rssRepository;

    @Autowired
    private RssMapper rssMapper;


    @Override
    public RssDTO getFullApiXmlEmission() {
        return rssMapper.toMapperRss(rssRepository.getServiceXmlService());
    }

}
