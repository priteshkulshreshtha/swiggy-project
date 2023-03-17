package com.swiggy.UserMicroService.exception;

public class RestaurantMismatchException extends RuntimeException {
    public RestaurantMismatchException(String message) {
        super(message);
    }

    public RestaurantMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
