package com.example.api.controller;

import com.example.api.domain.dto.RssDTO;
import com.example.api.domain.services.RssServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rss")
public class RssController {

    @Autowired
    private RssServices rssServices;

    @GetMapping("/get-emission")
    public ResponseEntity<RssDTO> getFullRssEmision(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(rssServices.getFullApiXmlEmission());
    }



}
