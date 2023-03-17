package com.swiggy.UserMicroService.beans.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.security.PrivilegedGetTccl;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponseBody {
    private  String message;
    private  String timeStamp = LocalDateTime.now().toString();
    private  String errorType;


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
