package com.example.javaspringbootrestusers.domain.errorhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@ControllerAdvice
public class DomainHandlerController {
    private void setRequestHttpToJson(HttpServletResponse response, String jsonMsg) throws IOException {
        response.setContentType("application/json");
        response.getWriter().write(jsonMsg);
        response.getWriter();
    }

    //Negocio
    @ExceptionHandler(DomainException.class)
    public void generalErrorJson(DomainException apiRestException, HttpServletResponse response) throws IOException {

        DomainMsgJsonError msgJsonError = new DomainMsgJsonError();
        msgJsonError.setMensaje(apiRestException.getMessage());
        String errorServletJson = new ObjectMapper().writeValueAsString(msgJsonError);
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        this.setRequestHttpToJson(response,errorServletJson);
    }
}
