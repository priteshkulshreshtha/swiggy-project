package com.swiggy.OrderMicroService.beans.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderRequest {
    private long restaurantId;
    private List<CartItemRequest> cartItems;
    private LocalDateTime timeOfOrder;
    private long customerId;
    private String customerLocation;

}
