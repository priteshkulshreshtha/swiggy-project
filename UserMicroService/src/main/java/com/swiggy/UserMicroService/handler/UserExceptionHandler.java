package com.swiggy.UserMicroService.handler;

import com.swiggy.UserMicroService.beans.response.ResponseWrapper;
import com.swiggy.UserMicroService.exception.BadResponseDataException;
import com.swiggy.UserMicroService.exception.ResourceAlreadyExistException;
import com.swiggy.UserMicroService.exception.ResourceNotFoundException;
import com.swiggy.UserMicroService.beans.response.ExceptionResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseWrapper> handleResourceNotFoundException(ResourceNotFoundException e) {

        ExceptionResponseBody response = new ExceptionResponseBody(
                e.getMessage(),
                "ResourceNotFoundException"
        );

        return new ResponseWrapper().getErrorResponse(response, e.getMessage() ,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ResourceAlreadyExistException.class})
    public ResponseEntity<ResponseWrapper> handleResourceAlreadyExistException(ResourceAlreadyExistException e) {

        ExceptionResponseBody errorBody = new ExceptionResponseBody(
                e.getMessage(),
                "ResourceAlreadyExistException"
        );

        return new ResponseWrapper().getErrorResponse(errorBody, e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {BadResponseDataException.class})
    public ResponseEntity<ResponseWrapper> handleBadResponseDataException(BadResponseDataException e) {

        ExceptionResponseBody errorBody = new ExceptionResponseBody(
                e.getMessage(),
                "BadResponseDataException"
        );

        return new ResponseWrapper().getErrorResponse(errorBody, e.getMessage(), HttpStatus.CONFLICT);
    }
}
