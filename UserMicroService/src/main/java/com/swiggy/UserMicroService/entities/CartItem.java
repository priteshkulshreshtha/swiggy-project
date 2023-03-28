package com.swiggy.UserMicroService.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.swiggy.UserMicroService.beans.request.FoodItemRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@JsonIgnoreProperties({"userCart", "cartItemId"})
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartItemId;
    private long foodId;
    private long basePrice=0;
    private long addOnPrice=0;
    private long quantity;

    @OneToMany(mappedBy = "cartItem")
    private Set<CustomizationField> customizationFields;



    @ManyToOne()
    @JoinColumn(name="user_cart_id")
    private UserCart userCart;

    public CartItem(long foodId, long foodPrice, long quantity) {
        this.foodId = foodId;
        this.basePrice = foodPrice;
        this.quantity = quantity;
    }

    public CartItem(FoodItemRequest foodItem) {
        this.foodId = foodItem.getId();
        this.basePrice = foodItem.getPrice();
        this.quantity = 1;
    }

}
