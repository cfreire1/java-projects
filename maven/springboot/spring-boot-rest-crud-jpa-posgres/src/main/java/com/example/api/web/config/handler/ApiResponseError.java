package com.example.api.web.config.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;


@Getter
@Setter
public class ApiResponseError{
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date timestamp;
    private String message;
    private String stackTrace;

    private ApiResponseError() {
        this.timestamp = new Date();
    }

    private String getStackTrace(Throwable exception){
        Writer writer = new StringWriter();
        exception.printStackTrace(new PrintWriter(writer));
        return  writer.toString();
    }


    public ApiResponseError(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiResponseError(HttpStatus status, Throwable exception) {
        this();
        this.status = status;
        this.message = exception.getMessage();
        this.stackTrace = this.getStackTrace(exception);
    }


}
