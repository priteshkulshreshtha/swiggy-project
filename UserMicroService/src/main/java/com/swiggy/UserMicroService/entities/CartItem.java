package com.swiggy.UserMicroService.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.swiggy.UserMicroService.beans.request.FoodItemRequest;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({"userCart", "cartItemId"})
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartItemId;
    private long foodId;
    private long foodPrice;
    private long quantity;

    @ManyToOne()
    @JoinColumn(name="user_cart_id")
    private UserCart userCart;

    public CartItem() {
    }

    public CartItem(long foodId, long foodPrice, long quantity) {
        this.foodId = foodId;
        this.foodPrice = foodPrice;
        this.quantity = quantity;
    }

    public CartItem(FoodItemRequest foodItem) {
        this.foodId = foodItem.getId();
        this.foodPrice = foodItem.getPrice();
        this.quantity = 1;
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    public long getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(long foodPrice) {
        this.foodPrice = foodPrice;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
