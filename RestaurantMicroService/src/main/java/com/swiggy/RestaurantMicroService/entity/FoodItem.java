package com.swiggy.RestaurantMicroService.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.swiggy.RestaurantMicroService.beans.request.NewFoodItemRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"restaurant", "customizationFields", "customizationCategories"})
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PRIVATE)
    private long id;
    private String name;
    private String description;

    @ManyToOne()
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;
    @OneToMany(mappedBy = "foodItem")
    private Set<CustomizationCategory> customizationCategories;
    @OneToMany(mappedBy = "foodItem")
    private Set<CustomizationField> customizationFields;

    public FoodItem(NewFoodItemRequest newFoodItemRequestBean) {
        this.setName(newFoodItemRequestBean.getName());
        this.setDescription(newFoodItemRequestBean.getDescription());
    }

    public FoodItem(NewFoodItemRequest newFoodItemRequestBean, Restaurant restaurant) {
        this(newFoodItemRequestBean);
        this.setRestaurant(restaurant);
    }
}
