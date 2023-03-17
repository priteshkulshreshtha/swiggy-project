package com.swiggy.OrderMicroService.handler;

import com.swiggy.OrderMicroService.beans.response.ExceptionResponseBody;
import com.swiggy.OrderMicroService.beans.response.ResponseWrapper;
import com.swiggy.OrderMicroService.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseWrapper> handleResourceNotFoundException(ResourceNotFoundException e) {

        ExceptionResponseBody responseBody = new ExceptionResponseBody(
                e.getMessage(),
                "ResourceNotFoundException"
        );

        return new ResponseWrapper().getErrorResponse(responseBody, e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
