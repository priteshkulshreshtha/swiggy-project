package com.swiggy.OrderMicroService.beans.response;


import com.swiggy.OrderMicroService.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentOrderResponse {
    private long id;
    private long restaurantId;
    private LocalDateTime timeOfOrder;
    private long customerId;
    private String customerLocation;

    public AgentOrderResponse(OrderEntity order) {
        this.id = order.getId();
        this.restaurantId = order.getRestaurantId();
        this.timeOfOrder = order.getTimeOfOrder();
        this.customerId = order.getCustomerId();
        this.customerLocation = order.getCustomerLocation();
    }
}
