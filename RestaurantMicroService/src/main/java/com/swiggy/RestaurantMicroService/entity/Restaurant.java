package com.swiggy.RestaurantMicroService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PRIVATE)
    private long id;
    private String cityName;
    private String name;

    @OneToMany(mappedBy = "restaurant")
    private Set<FoodItem> foodItemsList;


}
