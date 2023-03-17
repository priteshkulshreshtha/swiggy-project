package com.swiggy.DeliveryAgentMicroService.exception;

public class BadResponseDataException extends RuntimeException {

    public BadResponseDataException(String message) {
        super(message);
    }

    public BadResponseDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
