package com.example.gradlespringbootrestinfoproperties.controller;

import com.example.gradlespringbootrestinfoproperties.util.UtilProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/info")
public class InfoPropertiesController {

    @Autowired
    private UtilProperties utilProperties;

    @GetMapping("/{properties}")
    public ResponseEntity<Map<String, Object>> getAllProperties(@PathVariable("properties") String archive){
        return new ResponseEntity<>(utilProperties.getListProperties(archive), HttpStatus.OK);
    }
    @GetMapping("/find/{value}")
    public ResponseEntity<Map<String, Object>> findProperties(@PathVariable("value") String key){
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put(key, utilProperties.getPropertiesValue(key));
        return new ResponseEntity<>(mapOut, HttpStatus.OK);
    }



}
