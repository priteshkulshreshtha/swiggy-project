package com.swiggy.RestaurantMicroService.services;

import com.swiggy.RestaurantMicroService.beans.request.CustomizationRequest;
import com.swiggy.RestaurantMicroService.entity.CustomizationCategory;
import com.swiggy.RestaurantMicroService.entity.CustomizationField;
import com.swiggy.RestaurantMicroService.entity.FoodItem;
import com.swiggy.RestaurantMicroService.repository.CustomizationCategoryRepository;
import com.swiggy.RestaurantMicroService.repository.CustomizationFieldRepository;
import com.swiggy.RestaurantMicroService.repository.FoodItemRepository;
import com.swiggy.RestaurantMicroService.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomizationService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private FoodItemRepository foodItemRepository;
    @Autowired
    private CustomizationCategoryRepository categoryRepository;
    @Autowired
    private CustomizationFieldRepository fieldRepository;
    @Autowired
    FoodItemService foodItemService;

    public void addCustomization(long foodId, CustomizationRequest requestBody) {
        FoodItem foodItem = foodItemService.getFoodItemById(foodId);

        CustomizationCategory newCategory = new CustomizationCategory(requestBody.getCategoryName(), foodItem);
        categoryRepository.save(newCategory);

        requestBody.getFieldRequestList().forEach(field -> {
            CustomizationField newField = new CustomizationField(
                    field.getFieldName(),
                    field.getDescription(),
                    field.getPrice(),
                    newCategory,
                    foodItem
            );
            fieldRepository.save(newField);
        });


    }
}
