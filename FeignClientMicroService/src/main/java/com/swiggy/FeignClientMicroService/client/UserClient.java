package com.swiggy.FeignClientMicroService.client;

import com.swiggy.FeignClientMicroService.beans.request.userClient.CartItemRequest;
import com.swiggy.FeignClientMicroService.beans.response.ResponseWrapper;
import com.swiggy.FeignClientMicroService.beans.response.userClient.NewUserProfileRequest;
import com.swiggy.FeignClientMicroService.beans.response.userClient.UserProfileRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@FeignClient(name="user", url="http://localhost:8100")
public interface UserClient {

    @GetMapping("/user/{userId}")
    public ResponseWrapper getUserById(@PathVariable long userId);
    @PostMapping("/update_user")
    public ResponseWrapper updateUser(@RequestBody UserProfileRequest newUserProfile);

    @PostMapping("/user")
    public ResponseWrapper createUser(@RequestBody NewUserProfileRequest newUserProfile);

    @GetMapping("/restaurants/{userId}")
    public ResponseEntity<ResponseWrapper> getRestaurants(@PathVariable long userId);

    @GetMapping("restaurant/{restaurantId}/food_items")
    public ResponseEntity<ResponseWrapper> getFoodItemsByRestaurant(@PathVariable long restaurantId);

    @PostMapping("/user/{userId}/clear_cart")
    public ResponseEntity<ResponseWrapper> clearCartController(@PathVariable long userId);

    @PostMapping("/cart/add_item")
    public ResponseEntity<ResponseWrapper> addFoodItemController(@RequestBody CartItemRequest request);

    @GetMapping("/cart_items/{userId}")
    public ResponseEntity<ResponseWrapper> getCartOrderItemsController(@PathVariable long userId);

    @PostMapping("/cart/remove_item")
    public ResponseEntity<ResponseWrapper> removeFoodItemController(@RequestBody CartItemRequest request);

    @PostMapping("/place_order/{userId}")
    public ResponseEntity<ResponseWrapper> placeOrder(@PathVariable long userId);




    }
