package com.swiggy.OrderMicroService.beans.response;

import com.swiggy.OrderMicroService.entity.OrderCartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCartItemResponse {
    private long id;
    private long foodId;
    private long foodPrice;
    private long quantity;

    public OrderCartItemResponse(OrderCartItem order) {
        this.id = order.getId();
        this.foodId = order.getFoodId();
        this.foodPrice = order.getFoodPrice();
        this.quantity = order.getQuantity();
    }
}
