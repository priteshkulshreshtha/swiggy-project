package com.swiggy.UserMicroService.controller;

import com.swiggy.UserMicroService.beans.request.NewUserProfileRequest;
import com.swiggy.UserMicroService.beans.request.UserProfileRequest;
import com.swiggy.UserMicroService.beans.response.FoodItemResponse;
import com.swiggy.UserMicroService.beans.response.ResponseWrapper;
import com.swiggy.UserMicroService.beans.response.RestaurantResponse;
import com.swiggy.UserMicroService.entities.UserProfile;
import com.swiggy.UserMicroService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseWrapper> getUserByIdController(@PathVariable long userId) {
        UserProfile userProfile = userService.getUserById(userId);
        return new ResponseWrapper().getResponse(userProfile,
                "New user profile created",
                HttpStatus.CREATED
        );
    };

    @PostMapping("/update_user")
    public ResponseEntity<ResponseWrapper> updateUserProfileController(@RequestBody UserProfileRequest newUserProfile) {
        userService.updateUserProfileService(newUserProfile);
        return new ResponseWrapper().getResponse(null, "User profile updated", HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<ResponseWrapper> createUserProfileController(@RequestBody NewUserProfileRequest newUserProfileDetails) {
        userService.createUserProfileService(newUserProfileDetails);
        return new ResponseWrapper().getResponse(null,"User profile created", HttpStatus.CREATED);
    }

    @GetMapping("/restaurants/{userId}")
    public ResponseEntity<ResponseWrapper> getRestaurants(@PathVariable long userId) {
        List<RestaurantResponse> restaurants = new ArrayList<>();

        userService.getRestaurants(userId).forEach(restaurant -> {
            restaurants.add(new RestaurantResponse(restaurant));
        });

        return new ResponseWrapper().getResponse(restaurants,
                "Successfully fetched list of restaurants",
                HttpStatus.OK
        );
    }

    @GetMapping("restaurant/{restaurantId}/menu")
    public ResponseEntity<ResponseWrapper> getMenu(@PathVariable long restaurantId) {
        List<FoodItemResponse> menu = userService.getMenu(restaurantId);

        if (menu.isEmpty())
            return new ResponseWrapper().getResponse(
                menu,
                "No food Items present",
                HttpStatus.OK
            );


        return new ResponseWrapper().getResponse(menu,"Fetched list of food items", HttpStatus.OK);
    }

}
