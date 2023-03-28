package com.swiggy.RestaurantMicroService.services;

import com.swiggy.RestaurantMicroService.beans.request.NewFoodItemRequest;
import com.swiggy.RestaurantMicroService.beans.request.NewRestaurantRequest;
import com.swiggy.RestaurantMicroService.beans.response.CustomizationCategoryResponse;
import com.swiggy.RestaurantMicroService.beans.response.CustomizationFieldResponse;
import com.swiggy.RestaurantMicroService.beans.response.FoodItemResponse;
import com.swiggy.RestaurantMicroService.entity.CustomizationCategory;
import com.swiggy.RestaurantMicroService.entity.CustomizationField;
import com.swiggy.RestaurantMicroService.entity.FoodItem;
import com.swiggy.RestaurantMicroService.entity.Restaurant;
import com.swiggy.RestaurantMicroService.exception.ResourceAlreadyPresentException;
import com.swiggy.RestaurantMicroService.exception.ResourceNotFoundException;
import com.swiggy.RestaurantMicroService.repository.CustomizationCategoryRepository;
import com.swiggy.RestaurantMicroService.repository.CustomizationFieldRepository;
import com.swiggy.RestaurantMicroService.repository.FoodItemRepository;
import com.swiggy.RestaurantMicroService.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private FoodItemRepository foodItemRepository;
    @Autowired
    private CustomizationCategoryRepository categoryRepository;
    @Autowired
    private CustomizationFieldRepository fieldRepository;

    public List<Restaurant> getRestaurantByCity(String cityName) {
        return restaurantRepository.findByCityName(cityName);
    }

    public Restaurant getRestaurantById(long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isEmpty()) throw new ResourceNotFoundException("Restaurant not found");
        return restaurant.get();
    }

    public List<FoodItemResponse> getMenu(long restaurantId) {
        Restaurant restaurant = getRestaurantById(restaurantId);
        List<FoodItemResponse> foodItems = new ArrayList<>();


        foodItemRepository.findByRestaurant(restaurant).stream().forEach(foodItem -> {
            FoodItemResponse foodItemResponse = new FoodItemResponse(foodItem);
            foodItemResponse.setCustomizationCategoryList(getCategoryList(foodItem));
            foodItems.add(foodItemResponse);
        });


        return foodItems;
    }

    public List<CustomizationCategoryResponse> getCategoryList(FoodItem foodItem) {
        List<CustomizationCategoryResponse> categoryResponseList = new ArrayList<>();

        categoryRepository.findAllByFoodItem(foodItem).forEach(customizationCategory -> {
            CustomizationCategoryResponse category = new CustomizationCategoryResponse(customizationCategory);
            category.setFields(getFieldList(customizationCategory));
            categoryResponseList.add(category);
        });

        return categoryResponseList;
    }

    private List<CustomizationFieldResponse> getFieldList(CustomizationCategory customizationCategory) {

        List<CustomizationFieldResponse> fieldResponseList = new ArrayList<>();

        fieldRepository.findAllByCustomizationCategory(customizationCategory).forEach(customizationField -> {
            fieldResponseList.add(new CustomizationFieldResponse(customizationField));
        });

        return fieldResponseList;

    }

    public void createRestaurant(NewRestaurantRequest restaurantDetails) {

        Optional<Restaurant> restaurant = restaurantRepository.findFirstByCityNameAndName(
                restaurantDetails.getCityName(),
                restaurantDetails.getName()
        );

        if (restaurant.isPresent())
            throw new ResourceAlreadyPresentException("Restaurant already created.");

        Restaurant newRestaurant = new Restaurant();
        newRestaurant.setName(restaurantDetails.getName());
        newRestaurant.setCityName(restaurantDetails.getCityName());

        restaurantRepository.save(newRestaurant);
    }

    public void addFoodItem(NewFoodItemRequest foodDetails, long restaurantId) {
        Restaurant restaurant = getRestaurantById(restaurantId);

        if (!foodItemRepository.findFirstByNameAndRestaurant(foodDetails.getName(), restaurant).isEmpty())
            throw new ResourceAlreadyPresentException("The food item is already present in the restaurant, change name");

        FoodItem newFoodItem = new FoodItem(foodDetails, restaurant);
        foodItemRepository.save(newFoodItem);

        if (foodDetails.isCreateBaseCategory()) {
            CustomizationCategory category = new CustomizationCategory("Base", newFoodItem);

            CustomizationField field = new CustomizationField(
                    "Base",
                    "",
                    foodDetails.getPrice(),
                    category,
                    newFoodItem
            );

            categoryRepository.save(category);
            fieldRepository.save(field);
        }
    }
}
