package com.swiggy.UserMicroService.beans.request;

public class RestaurantRequest {

    private long id;
    private String name;

    public RestaurantRequest() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
