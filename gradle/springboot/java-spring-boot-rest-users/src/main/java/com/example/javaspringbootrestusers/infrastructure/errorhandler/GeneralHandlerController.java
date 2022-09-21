package com.example.javaspringbootrestusers.infrastructure.errorhandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GeneralHandlerController {
    private void setRequestHttpToJson(HttpServletResponse response, String jsonMsg) throws IOException {
        response.setContentType("application/json");
        response.getWriter().write(jsonMsg);
        response.getWriter();
    }

    @ExceptionHandler(GeneralException.class)
    public void generalErrorJson(GeneralException generalException, HttpServletResponse response) throws IOException {

        GeneralMsgJsonError msgJsonError = new GeneralMsgJsonError();
        msgJsonError.setMensaje(generalException.getMessage());
        String errorServletJson = new ObjectMapper().writeValueAsString(msgJsonError);
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        this.setRequestHttpToJson(response,errorServletJson);
    }

    @ExceptionHandler(NumberFormatException.class)
    public void generalErrorJson(NumberFormatException numberFormatException,HttpServletResponse response) throws IOException {

        GeneralMsgJsonError msgJsonError = new GeneralMsgJsonError();
        msgJsonError.setMensaje(numberFormatException.getMessage());
        String errorServletJson = new ObjectMapper().writeValueAsString(msgJsonError);
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        this.setRequestHttpToJson(response,errorServletJson);
    }

    @ExceptionHandler(JsonParseException.class)
    public void generalErrorJson(JsonParseException jsonParseException, HttpServletResponse response) throws IOException {

        GeneralMsgJsonError msgJsonError = new GeneralMsgJsonError();
        msgJsonError.setMensaje(jsonParseException.getMessage());
        String errorServletJson = new ObjectMapper().writeValueAsString(msgJsonError);
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        this.setRequestHttpToJson(response,errorServletJson);
    }

    @ExceptionHandler(JsonMappingException.class)
    public void generalErrorJson(JsonMappingException jsonMappingException, HttpServletResponse response) throws IOException {

        GeneralMsgJsonError msgJsonError = new GeneralMsgJsonError();
        msgJsonError.setMensaje(jsonMappingException.getMessage());
        String errorServletJson = new ObjectMapper().writeValueAsString(msgJsonError);
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        this.setRequestHttpToJson(response,errorServletJson);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void generalErrorJson(HttpMessageNotReadableException httpMessageNotReadableException, HttpServletResponse response) throws IOException {

        GeneralMsgJsonError msgJsonError = new GeneralMsgJsonError();
        msgJsonError.setMensaje(httpMessageNotReadableException.getMessage());
        String errorServletJson = new ObjectMapper().writeValueAsString(msgJsonError);
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        this.setRequestHttpToJson(response,errorServletJson);
    }

}
