package com.swiggy.RestaurantMicroService.services;

import com.swiggy.RestaurantMicroService.entity.FoodItem;
import com.swiggy.RestaurantMicroService.exception.ResourceNotFoundException;
import com.swiggy.RestaurantMicroService.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodItemService {

    @Autowired
    FoodItemRepository foodItemRepository;

    public FoodItem getFoodItemById(long foodId) {
        Optional<FoodItem> foodItem = foodItemRepository.findById(foodId);
        if (foodItem.isEmpty()) throw new ResourceNotFoundException("Food item not found");
        return foodItem.get();
    }
}
