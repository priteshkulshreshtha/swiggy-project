package com.swiggy.RestaurantMicroService.beans.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomizationFieldRequest {
    private String fieldName;
    private String description;
    private int price;

}
