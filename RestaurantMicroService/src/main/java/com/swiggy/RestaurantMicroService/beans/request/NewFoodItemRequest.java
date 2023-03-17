package com.swiggy.RestaurantMicroService.beans.request;

import com.swiggy.RestaurantMicroService.entity.Restaurant;

public class NewFoodItemRequest {
    private String name;
    private String description;
    private int price;
    private Restaurant restaurant;

    public NewFoodItemRequest(String name, String description, int price, Restaurant restaurant) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }

    public NewFoodItemRequest() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
