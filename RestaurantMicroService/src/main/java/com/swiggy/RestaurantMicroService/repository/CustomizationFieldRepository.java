package com.swiggy.RestaurantMicroService.repository;

import com.swiggy.RestaurantMicroService.entity.CustomizationCategory;
import com.swiggy.RestaurantMicroService.entity.CustomizationField;
import com.swiggy.RestaurantMicroService.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomizationFieldRepository extends JpaRepository<CustomizationField, Long> {
    List<CustomizationField> findAllByFoodItem(FoodItem foodItem);
    List<CustomizationField> findAllByCustomizationCategory(CustomizationCategory customizationCategory);

}
