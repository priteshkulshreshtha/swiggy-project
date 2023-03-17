package com.swiggy.RestaurantMicroService.repository;

import com.swiggy.RestaurantMicroService.entity.FoodItem;
import com.swiggy.RestaurantMicroService.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    public List<FoodItem> findByRestaurant(Restaurant restaurant);
    public Optional<FoodItem> findFirstByNameAndRestaurant(String foodName, Restaurant restaurant);
}
