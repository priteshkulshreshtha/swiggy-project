package com.swiggy.UserMicroService.beans.response;

import com.swiggy.UserMicroService.entities.CartItem;

public class CartItemResponse {
    private long foodId;
    private long foodPrice;
    private long quantity;

    public CartItemResponse(long foodId, long foodPrice, long quantity) {
        this.foodId = foodId;
        this.foodPrice = foodPrice;
        this.quantity = quantity;
    }

    public CartItemResponse(CartItem cartItem) {
        this.foodId = cartItem.getCartItemId();
        this.foodPrice = cartItem.getFoodPrice();
        this.quantity = cartItem.getQuantity();
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
