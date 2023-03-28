package com.swiggy.RestaurantMicroService.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"customizationCategory", "foodItem"})
public class CustomizationField {
    @Id
    @GeneratedValue
    private long id;
    private String fieldName;
    private String description;
    private int price;
    @ManyToOne()
    @JoinColumn(name="customization_category_id")
    private CustomizationCategory customizationCategory;
    @ManyToOne()
    @JoinColumn(name="food_item_id")
    private FoodItem foodItem;

    public CustomizationField(String fieldName, String description, int price, CustomizationCategory customizationCategory, FoodItem foodItem) {
        this.fieldName = fieldName;
        this.description = description;
        this.price = price;
        this.customizationCategory = customizationCategory;
        this.foodItem = foodItem;
    }
}
