package com.swiggy.RestaurantMicroService.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.swiggy.RestaurantMicroService.beans.request.NewFoodItemRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"restaurant"})
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PRIVATE)
    private long id;
    private String name;
    private String description;
    private int price;

    @ManyToOne()
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;

    public FoodItem(NewFoodItemRequest newFoodItemRequestBean) {
        this.setName(newFoodItemRequestBean.getName());
        this.setDescription(newFoodItemRequestBean.getDescription());
        this.setPrice(newFoodItemRequestBean.getPrice());
    }

    public FoodItem(NewFoodItemRequest newFoodItemRequestBean, Restaurant restaurant) {
        this(newFoodItemRequestBean);
        this.setRestaurant(restaurant);
    }
}
