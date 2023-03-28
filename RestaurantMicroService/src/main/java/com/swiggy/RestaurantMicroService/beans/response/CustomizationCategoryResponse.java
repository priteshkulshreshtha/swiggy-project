package com.swiggy.RestaurantMicroService.beans.response;

import com.swiggy.RestaurantMicroService.entity.CustomizationCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomizationCategoryResponse {
    private String categoryName;
    private List<CustomizationFieldResponse> fields;

    public CustomizationCategoryResponse(CustomizationCategory customizationCategory) {
        this.categoryName = customizationCategory.getCategoryName();
    }
}
