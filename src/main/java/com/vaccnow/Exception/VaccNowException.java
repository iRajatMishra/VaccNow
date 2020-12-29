package com.vaccnow.Exception;

import org.springframework.http.HttpStatus;

public class VaccNowException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;

    public VaccNowException(String exceptionType, HttpStatus httpStatus) {
        this.message = exceptionType;
        this.httpStatus = httpStatus;
    }
}
