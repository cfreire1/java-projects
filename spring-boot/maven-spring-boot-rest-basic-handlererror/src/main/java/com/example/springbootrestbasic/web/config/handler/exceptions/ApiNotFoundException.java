package com.example.springbootrestbasic.web.config.handler.exceptions;


public class ApiNotFoundException extends RuntimeException {

    public ApiNotFoundException() {
    }

    public ApiNotFoundException(String message) {
        super(message);
    }

    public ApiNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiNotFoundException(Throwable cause) {
        super(cause);
    }

    public ApiNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
