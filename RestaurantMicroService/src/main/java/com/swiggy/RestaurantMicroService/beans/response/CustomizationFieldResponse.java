package com.swiggy.RestaurantMicroService.beans.response;

import com.swiggy.RestaurantMicroService.entity.CustomizationField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomizationFieldResponse {
    private long fieldId;
    private String fieldName;
    private String description;
    private int price;

    public CustomizationFieldResponse(CustomizationField customizationField) {
        this.fieldId = customizationField.getId();
        this.fieldName = customizationField.getFieldName();
        this.description = customizationField.getDescription();
        this.price = customizationField.getPrice();
    }
}
