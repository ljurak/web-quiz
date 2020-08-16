package com.example.webquiz.model.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiError {

    private final HttpStatus httpStatus;

    private final List<String> errors;

    public ApiError(HttpStatus httpStatus, List<String> errors) {
        this.httpStatus = httpStatus;
        this.errors = errors;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public List<String> getErrors() {
        return errors;
    }
}
