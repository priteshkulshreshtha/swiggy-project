package com.swiggy.OrderMicroService.beans.response;

import org.apache.tomcat.util.security.PrivilegedGetTccl;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExceptionResponseBody {
    private final String message;
    private final LocalDateTime timeStamp = LocalDateTime.now();
    private final String errorType;


    public ExceptionResponseBody(String message, String errorType) {
        this.message = message;
        this.errorType = errorType;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
    public String getMessage() {
        return message;
    }
    public String getErrorType() {
        return errorType;
    }
}
