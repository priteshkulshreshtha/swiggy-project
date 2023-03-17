package com.swiggy.RestaurantMicroService.repository;

import com.swiggy.RestaurantMicroService.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    public List<Restaurant> findByCityName(String cityName);

    Optional<Restaurant> findFirstByCityNameAndName(String cityName, String name);
}
