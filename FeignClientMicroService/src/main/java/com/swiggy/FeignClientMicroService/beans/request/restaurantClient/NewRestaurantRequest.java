package com.swiggy.FeignClientMicroService.beans.request.restaurantClient;

public class NewRestaurantRequest {
    private String cityName;
    private String name;

    public NewRestaurantRequest() {
    }

    public String getCityName() {
        return cityName;
    }

    public String getName() {
        return name;
    }
}
