package com.swiggy.UserMicroService.beans.response;

import com.swiggy.UserMicroService.beans.request.FoodItemRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    }

    public FoodItemResponse(FoodItemRequest foodItem) {
        this.id = foodItem.getId();
        this.name = foodItem.getName();
        this.description = foodItem.getDescription();
    }

}
