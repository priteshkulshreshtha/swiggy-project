package com.swiggy.FeignClientMicroService.beans.request.restaurantClient;

public class NewFoodItemRequest {
    private String name;
    private String description;
    private int price;

    public NewFoodItemRequest(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
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

}
