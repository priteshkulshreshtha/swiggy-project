package com.swiggy.RestaurantMicroService.beans.response;

import com.swiggy.RestaurantMicroService.entity.Restaurant;

public class RestaurantResponse {

    private long id;
    private String name;

    public RestaurantResponse(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RestaurantResponse(Restaurant restaurant) {
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
