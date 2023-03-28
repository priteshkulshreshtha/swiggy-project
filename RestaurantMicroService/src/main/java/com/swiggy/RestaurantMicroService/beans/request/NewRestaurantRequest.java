package com.swiggy.RestaurantMicroService.beans.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewRestaurantRequest {
    private String cityName;
    private String name;

}
