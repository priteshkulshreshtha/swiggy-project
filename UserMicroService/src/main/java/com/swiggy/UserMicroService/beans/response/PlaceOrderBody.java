package com.swiggy.UserMicroService.beans.response;

import com.swiggy.UserMicroService.entities.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderBody {
    private long restaurantId;
    private List<CartItemResponse> cartItems = new ArrayList<>();
    private LocalDateTime timeOfOrder;
    private long customerId;
    private String customerLocation;
    private int billAmount;

    public void setCartItems(List<CartItem> cartItems) {
        cartItems.stream().forEach(cartItem -> {
            getCartItems().add(new CartItemResponse(cartItem));
        });
    }
}
