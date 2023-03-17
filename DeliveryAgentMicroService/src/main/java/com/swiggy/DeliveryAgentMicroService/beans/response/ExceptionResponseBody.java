package com.swiggy.DeliveryAgentMicroService.beans.response;

import org.apache.tomcat.util.security.PrivilegedGetTccl;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExceptionResponseBody {
    private final String message;
    private String timeStamp = LocalDateTime.now().toString();
    private final String errorType;


    public ExceptionResponseBody(String message, String errorType) {
        this.message = message;
        this.errorType = errorType;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
    public String getMessage() {
        return message;
    }
    public String getErrorType() {
        return errorType;
    }
}
