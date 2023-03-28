package com.swiggy.UserMicroService.beans.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomizationCategoryResponse {
    private String categoryName;
    private List<CustomizationFieldResponse> fields;


}
