package com.swiggy.UserMicroService.handler;

import com.swiggy.UserMicroService.exception.RestaurantMismatchException;
import com.swiggy.UserMicroService.beans.response.ExceptionResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserCartExceptionHandler {

    @ExceptionHandler(RestaurantMismatchException.class)
    public ResponseEntity<ExceptionResponseBody> handleRestaurantMismatchException(RestaurantMismatchException e) {

        ExceptionResponseBody response = new ExceptionResponseBody(
                e.getMessage(),
                RestaurantMismatchException.class.toString()
        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);

    }


}
