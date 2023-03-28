package com.swiggy.RestaurantMicroService.controller;

import com.swiggy.RestaurantMicroService.beans.request.CustomizationRequest;
import com.swiggy.RestaurantMicroService.beans.response.ResponseWrapper;
import com.swiggy.RestaurantMicroService.entity.CustomizationCategory;
import com.swiggy.RestaurantMicroService.services.CustomizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomizationController {

    @Autowired
    CustomizationService customizationService;

    @PostMapping("/food_item/{foodId}/add_customization")
    public ResponseEntity<ResponseWrapper> addCustomization(
            @PathVariable long foodId,
            @RequestBody CustomizationRequest requestBody
    ) {

        customizationService.addCustomization(foodId, requestBody);


        return new ResponseWrapper().getResponse(null, "Customization Set Successfully",HttpStatus.OK);
    }

}
