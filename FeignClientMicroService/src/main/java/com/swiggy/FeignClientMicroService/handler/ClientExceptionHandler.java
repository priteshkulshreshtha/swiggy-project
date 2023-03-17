package com.swiggy.FeignClientMicroService.handler;

import com.swiggy.FeignClientMicroService.beans.response.ExceptionResponseBody;
import com.swiggy.FeignClientMicroService.beans.response.ResponseWrapper;
import com.swiggy.FeignClientMicroService.exceptions.BadResponseDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientExceptionHandler {

    @ExceptionHandler(BadResponseDataException.class)
    public ResponseEntity<ResponseWrapper> handleBadResposneDataException(BadResponseDataException e) {
        ExceptionResponseBody errorBody = new ExceptionResponseBody(
                e.getMessage(),
                "BadResponseDataException"
        );

        return new ResponseWrapper().getErrorResponse(errorBody, e.getMessage(), HttpStatus.CONFLICT);

    }
}
