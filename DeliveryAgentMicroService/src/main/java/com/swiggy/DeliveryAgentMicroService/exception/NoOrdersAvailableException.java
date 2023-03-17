package com.swiggy.DeliveryAgentMicroService.exception;

public class NoOrdersAvailableException extends RuntimeException {

    public NoOrdersAvailableException(String message) {
        super(message);
    }

    public NoOrdersAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
