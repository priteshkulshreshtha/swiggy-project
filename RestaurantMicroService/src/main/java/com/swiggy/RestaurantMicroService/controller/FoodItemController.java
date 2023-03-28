package com.swiggy.RestaurantMicroService.controller;

import com.swiggy.RestaurantMicroService.beans.response.FoodItemResponse;
import com.swiggy.RestaurantMicroService.beans.response.ResponseWrapper;
import com.swiggy.RestaurantMicroService.services.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodItemController {

    @Autowired
    FoodItemService foodItemService;

    @GetMapping("/food_item/{foodId}")
    public ResponseEntity<ResponseWrapper> getFoodItemById(@PathVariable long foodId) {
        FoodItemResponse foodItem = foodItemService.getFoodItem(foodId);
        return new ResponseWrapper().getResponse(foodItem, "Fetched food item",HttpStatus.OK);
    }
}
