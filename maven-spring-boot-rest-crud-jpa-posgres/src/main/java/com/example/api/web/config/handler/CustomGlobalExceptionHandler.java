package com.example.api.web.config.handler;

import com.example.api.web.config.handler.exceptions.ApiNotFoundException;
import com.example.api.web.config.handler.exceptions.ApiNotModifiedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * https://zetcode.com/springboot/controlleradvice/
 * https://mkyong.com/spring-boot/spring-rest-error-handling-example/
 * https://www.toptal.com/java/spring-boot-rest-api-error-handling
 *
 */
@ControllerAdvice
public class CustomGlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    private void setRequestHttpToJson(HttpServletResponse response, String jsonMsg) throws IOException {
        response.setContentType("application/json");
        response.getWriter().write(jsonMsg);
        response.getWriter();
    }

    @ExceptionHandler(ApiNotFoundException.class)
    public void notFound(ApiNotFoundException notFoundException,HttpServletResponse response) throws IOException {

            ApiResponseError responseError = new ApiResponseError(HttpStatus.NOT_FOUND, notFoundException);
            String ErrorServletJson = new ObjectMapper().writeValueAsString(responseError);
            response.setStatus(HttpStatus.NOT_FOUND.value());

            this.setRequestHttpToJson(response,ErrorServletJson);
    }

    @ExceptionHandler(ApiNotModifiedException.class)
    public void notModified(ApiNotModifiedException apiNotModifiedException,HttpServletResponse response) throws IOException {

        ApiResponseError responseError = new ApiResponseError(HttpStatus.NO_CONTENT, apiNotModifiedException);
        String ErrorServletJson = new ObjectMapper().writeValueAsString(responseError);
        response.setStatus(HttpStatus.NO_CONTENT.value());

        this.setRequestHttpToJson(response,ErrorServletJson);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public void authErrorCredential(BadCredentialsException badCredentialsException, HttpServletResponse response) throws IOException {

        ApiResponseError responseError = new ApiResponseError(HttpStatus.FORBIDDEN, badCredentialsException);
        String ErrorServletJson = new ObjectMapper().writeValueAsString(responseError);
        response.setStatus(HttpStatus.FORBIDDEN.value());

        this.setRequestHttpToJson(response,ErrorServletJson);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public void authErrorServer(InternalAuthenticationServiceException internalAuthenticationServiceException, HttpServletResponse response) throws IOException {

        ApiResponseError responseError = new ApiResponseError(HttpStatus.SERVICE_UNAVAILABLE, internalAuthenticationServiceException);
        String ErrorServletJson = new ObjectMapper().writeValueAsString(responseError);
        response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());

        this.setRequestHttpToJson(response,ErrorServletJson);
    }


}
