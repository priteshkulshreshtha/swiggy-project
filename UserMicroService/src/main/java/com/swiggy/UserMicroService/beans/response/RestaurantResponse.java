package com.swiggy.UserMicroService.beans.response;

import com.swiggy.UserMicroService.beans.request.RestaurantRequest;

public class RestaurantResponse {
    private long id;
    private String name;

    public RestaurantResponse(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RestaurantResponse(RestaurantRequest restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
