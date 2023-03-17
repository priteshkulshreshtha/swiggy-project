package com.swiggy.RestaurantMicroService.handler;

import com.swiggy.RestaurantMicroService.beans.response.ResponseWrapper;
import com.swiggy.RestaurantMicroService.exception.ResourceAlreadyPresentException;
import com.swiggy.RestaurantMicroService.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.swiggy.RestaurantMicroService.beans.response.ExceptionResponseBody;

@ControllerAdvice
public class RestaurantExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseWrapper> handleRestaurantNotFoundException(ResourceNotFoundException e) {


        ExceptionResponseBody response = new ExceptionResponseBody(
                e.getMessage(),
                "ResourceNotFoundException"
        );

        return new ResponseWrapper().getErrorResponse(response, e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyPresentException.class)
    public ResponseEntity<ResponseWrapper> handleFoodItemAlreadyPresent(ResourceAlreadyPresentException e) {

        ExceptionResponseBody response = new ExceptionResponseBody(
                e.getMessage(),
                "ResourceAlreadyPresentException"
        );

        return new ResponseWrapper().getErrorResponse(response, e.getMessage(), HttpStatus.CONFLICT);
    }

}
