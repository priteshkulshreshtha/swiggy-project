package com.swiggy.DeliveryAgentMicroService.exception;

public class DeliveryAlreadyInProgress extends RuntimeException{
    public DeliveryAlreadyInProgress(String message) {
        super(message);
    }

    public DeliveryAlreadyInProgress(String message, Throwable cause) {
        super(message, cause);
    }
}
