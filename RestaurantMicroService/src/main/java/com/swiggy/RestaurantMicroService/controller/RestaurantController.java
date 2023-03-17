package com.swiggy.RestaurantMicroService.controller;

import com.swiggy.RestaurantMicroService.beans.request.NewFoodItemRequest;
import com.swiggy.RestaurantMicroService.beans.request.NewRestaurantRequest;
import com.swiggy.RestaurantMicroService.beans.response.FoodItemResponse;
import com.swiggy.RestaurantMicroService.beans.response.ResponseWrapper;
import com.swiggy.RestaurantMicroService.beans.response.RestaurantResponse;
import com.swiggy.RestaurantMicroService.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<ResponseWrapper> getRestaurantById(@PathVariable long id) {
        return new ResponseWrapper().getResponse(
                new RestaurantResponse(restaurantService.getRestaurantById(id)),
                "Fetched restaurant successfully",
                HttpStatus.CREATED
        );
    }

    @GetMapping("/restaurant/city/{cityName}")
    public ResponseEntity<ResponseWrapper> getRestaurantByCity(@PathVariable String cityName) {

        ArrayList<RestaurantResponse> restaurantList = new ArrayList<>();
        restaurantService.getRestaurantByCity(cityName).stream().forEach( restaurant ->  {
            restaurantList.add(new RestaurantResponse(restaurant));
        });

        if(restaurantList.isEmpty()) {
            return new ResponseWrapper().getResponse(
                    restaurantList,
                    "No restaurants are registered in " + cityName,
                    HttpStatus.OK
            );
        }

        return new ResponseWrapper().getResponse(
                restaurantList,
                "Fetched restaurants in " + cityName,
                HttpStatus.OK
        );
    }

    @GetMapping("/restaurant/{restaurantId}/food")
    public ResponseEntity<ResponseWrapper> getFoodItemsByRestaurant(@PathVariable long restaurantId) {
        List<FoodItemResponse> foodItemResponses = restaurantService.getFoodItemsByRestaurant(restaurantId);

        if (foodItemResponses.isEmpty())
            return new ResponseWrapper().getResponse(
                    foodItemResponses,
                    "No food Items are present in the restaurant",
                    HttpStatus.OK
            );

        return new ResponseWrapper().getResponse(
                foodItemResponses,
                "Fetched food items served",
                HttpStatus.OK
        );
    }

    @PostMapping("/restaurant")
    public ResponseEntity<ResponseWrapper> createRestaurant(@RequestBody NewRestaurantRequest restaurantDetails) {
        restaurantService.createRestaurant(restaurantDetails);
        return new ResponseWrapper().getResponse(null,"Restaurant added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/restaurant/{restaurantId}/food")
    public ResponseEntity<ResponseWrapper> addFoodItem(
            @RequestBody NewFoodItemRequest foodDetails,
            @PathVariable long restaurantId
    ) {
        restaurantService.addFoodItem(foodDetails, restaurantId);
        return new ResponseWrapper().getResponse(null,"Food Item added Successfully", HttpStatus.OK);
    }

}
