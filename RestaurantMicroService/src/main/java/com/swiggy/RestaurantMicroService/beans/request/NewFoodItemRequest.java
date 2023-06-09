package com.swiggy.RestaurantMicroService.beans.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewFoodItemRequest {
    private String name;
    private String description;
    private int price;
    private boolean createBaseCategory;

}
