package com.example.springbootrestbasic.web.config.handler.exceptions;


public class ApiNotModifiedException extends RuntimeException {

    public ApiNotModifiedException() {
    }

    public ApiNotModifiedException(String message) {
        super(message);
    }

    public ApiNotModifiedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiNotModifiedException(Throwable cause) {
        super(cause);
    }

    public ApiNotModifiedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
