package com.swiggy.RestaurantMicroService.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"foodItem", "customizationFields"})
public class CustomizationCategory {
    @Id
    @GeneratedValue
    private long id;
    private String categoryName;
    @ManyToOne()
    @JoinColumn(name="food_item_id")
    private FoodItem foodItem;
    @OneToMany(mappedBy = "customizationCategory")
    private Set<CustomizationField> customizationFields;

    public CustomizationCategory(String categoryName, FoodItem foodItem) {
        this.categoryName = categoryName;
        this.foodItem = foodItem;
    }
}
