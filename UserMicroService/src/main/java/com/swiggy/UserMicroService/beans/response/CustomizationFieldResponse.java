package com.swiggy.UserMicroService.beans.response;

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

}
