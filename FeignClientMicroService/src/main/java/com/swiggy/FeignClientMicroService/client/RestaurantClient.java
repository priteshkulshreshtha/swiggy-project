package com.swiggy.FeignClientMicroService.client;

import com.swiggy.FeignClientMicroService.beans.request.restaurantClient.NewFoodItemRequest;
import com.swiggy.FeignClientMicroService.beans.request.restaurantClient.NewRestaurantRequest;
import com.swiggy.FeignClientMicroService.beans.response.ResponseWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="restaurant", url = "http://localhost:8200")
public interface RestaurantClient {

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<ResponseWrapper> getRestaurantById(@PathVariable long id);

    @GetMapping("/restaurant/city/{cityName}")
    public ResponseEntity<ResponseWrapper> getRestaurantByCity(@PathVariable String cityName);

    @GetMapping("/restaurant/{restaurantId}/food")
    public ResponseEntity<ResponseWrapper> getFoodItemsByRestaurant(@PathVariable long restaurantId);

    @PostMapping("/restaurant")
    public ResponseEntity<ResponseWrapper> createRestaurant(@RequestBody NewRestaurantRequest restaurantDetails);

    @PostMapping("/restaurant/{restaurantId}/food")
    public ResponseEntity<ResponseWrapper> addFoodItem(
            @RequestBody NewFoodItemRequest foodDetails,
            @PathVariable long restaurantId
    );

    @GetMapping("/food_item/{foodId}")
    public ResponseEntity<ResponseWrapper> getFoodItemById(@PathVariable long foodId);
}
