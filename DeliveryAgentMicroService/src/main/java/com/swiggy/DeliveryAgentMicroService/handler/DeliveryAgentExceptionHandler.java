package com.swiggy.DeliveryAgentMicroService.handler;

import com.swiggy.DeliveryAgentMicroService.beans.response.ExceptionResponseBody;
import com.swiggy.DeliveryAgentMicroService.beans.response.ResponseWrapper;
import com.swiggy.DeliveryAgentMicroService.exception.DeliveryAlreadyInProgress;
import com.swiggy.DeliveryAgentMicroService.exception.NoOrdersAvailableException;
import com.swiggy.DeliveryAgentMicroService.exception.ResourceAlreadyPresentException;
import com.swiggy.DeliveryAgentMicroService.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class DeliveryAgentExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseWrapper> handleAgentNotFoundException(ResourceNotFoundException e) {

        ExceptionResponseBody response = new ExceptionResponseBody(
                e.getMessage(),
                "ResourceNotFoundException"
        );

        return new ResponseWrapper().getErrorResponse(response, e.getMessage(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ResourceAlreadyPresentException.class)
    public ResponseEntity<ResponseWrapper> handleResourceAlreadyPresentException(ResourceAlreadyPresentException e) {

        ExceptionResponseBody responseBody = new ExceptionResponseBody(
                e.getMessage(),
                "ResourceAlreadyPresentException"
        );

        return new ResponseWrapper().getErrorResponse(responseBody, e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoOrdersAvailableException.class)
    public ResponseEntity<ResponseWrapper> handleNoOrdersAvailableException(NoOrdersAvailableException e) {

        ExceptionResponseBody responseBody = new ExceptionResponseBody(
                e.getMessage(),
                "NoOrdersAvailableException"
        );

        return new ResponseWrapper().getErrorResponse(responseBody, e.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(DeliveryAlreadyInProgress.class)
    public ResponseEntity<ResponseWrapper> handleDeliveryAlreadyInProgress(DeliveryAlreadyInProgress e) {

        ExceptionResponseBody responseBody = new ExceptionResponseBody(
                e.getMessage(),
                "DeliveryAlreadyInProgress"
        );

        return new ResponseWrapper().getErrorResponse(responseBody, e.getMessage(), HttpStatus.CONFLICT);
    }



}
