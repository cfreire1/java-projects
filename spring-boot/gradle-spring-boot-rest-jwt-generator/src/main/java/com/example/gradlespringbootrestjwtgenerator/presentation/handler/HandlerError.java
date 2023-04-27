package com.example.gradlespringbootrestjwtgenerator.presentation.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class HandlerError {

    @ExceptionHandler({ Exception.class})
    public ResponseEntity handleException(Exception ex) {

        Map<String, Object> msgError = new HashMap<>();
        msgError.put("Error",ex.getLocalizedMessage());
        msgError.put("Exception", ex.getClass().toString());

        return new ResponseEntity(msgError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
