package com.swiggy.OrderMicroService.beans.response;

import com.swiggy.OrderMicroService.entity.OrderEntity;
import com.swiggy.OrderMicroService.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private long id;
    private long restaurantId;
    private List<OrderCartItemResponse> orderItems = new ArrayList<>();
    private Status status;
    private LocalDateTime timeOfOrder;
    private LocalDateTime timeOfDelivery;
    private long deliveryAgentId;
    private long customerId;
    private String customerLocation;

    public OrderResponse(OrderEntity order) {
        this.id = order.getId();
        this.restaurantId = order.getRestaurantId();
        this.status = order.getStatus();
        this.timeOfOrder = order.getTimeOfOrder();
        this.timeOfDelivery = order.getTimeOfDelivery();
        this.deliveryAgentId = order.getDeliveryAgentId();
        this.customerId = order.getCustomerId();
        this.customerLocation = order.getCustomerLocation();
    }
}
