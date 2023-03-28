package com.swiggy.RestaurantMicroService.beans.response;

import com.swiggy.RestaurantMicroService.entity.CustomizationCategory;
import com.swiggy.RestaurantMicroService.entity.FoodItem;
import lombok.Data;

import java.util.List;

@Data
public class FoodItemResponse {
    private long id;
    private String name;
    private String description;
    private long restaurantId;
    private List<CustomizationCategoryResponse> customizationCategoryList;

    public FoodItemResponse(long id, String name, String description, long restaurantId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.restaurantId = restaurantId;
    }

    public FoodItemResponse(FoodItem foodItem) {
        this.id = foodItem.getId();
        this.name = foodItem.getName();
        this.description = foodItem.getDescription();
        this.restaurantId = foodItem.getRestaurant().getId();
    }

}
