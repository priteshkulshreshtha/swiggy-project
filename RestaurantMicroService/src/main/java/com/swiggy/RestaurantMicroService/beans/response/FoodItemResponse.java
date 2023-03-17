package com.swiggy.RestaurantMicroService.beans.response;

import com.swiggy.RestaurantMicroService.entity.FoodItem;

public class FoodItemResponse {
    private long id;
    private String name;
    private String description;
    private int price;
    private long restaurantId;

    public FoodItemResponse(long id, String name, String description, int price, long restaurantId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public FoodItemResponse(FoodItem foodItem) {
        this.id = foodItem.getId();
        this.name = foodItem.getName();
        this.description = foodItem.getDescription();
        this.price = foodItem.getPrice();
        this.restaurantId = foodItem.getRestaurant().getId();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
