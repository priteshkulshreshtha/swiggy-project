package com.swiggy.RestaurantMicroService.services;

import com.swiggy.RestaurantMicroService.beans.response.FoodItemResponse;
import com.swiggy.RestaurantMicroService.entity.FoodItem;
import com.swiggy.RestaurantMicroService.entity.Restaurant;
import com.swiggy.RestaurantMicroService.exception.ResourceNotFoundException;
import com.swiggy.RestaurantMicroService.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    @Autowired
    FoodItemRepository foodItemRepository;

    @Autowired
    RestaurantService restaurantService;

    public FoodItem getFoodItemById(long foodId) {
        Optional<FoodItem> foodItem = foodItemRepository.findById(foodId);
        if (foodItem.isEmpty()) throw new ResourceNotFoundException("Food item not found");
        return foodItem.get();

    }

    public FoodItemResponse getFoodItem(long foodId) {
        FoodItem foodItem = getFoodItemById(foodId);
        FoodItemResponse foodItemResponse = new FoodItemResponse(foodItem);
        foodItemResponse.setCustomizationCategoryList(restaurantService.getCategoryList(foodItem));
        return foodItemResponse;
    }
}
