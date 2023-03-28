package com.swiggy.RestaurantMicroService.beans.request;

import com.swiggy.RestaurantMicroService.entity.CustomizationField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomizationRequest {
    private String categoryName;
    private List<CustomizationFieldRequest> fieldRequestList;
}
