package com.swiggy.OrderMicroService.entity;

import com.swiggy.OrderMicroService.beans.request.CartItemRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long foodId;
    private long foodPrice;
    private long quantity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    public OrderCartItem(CartItemRequest cartItem, OrderEntity order) {
        this.foodId = cartItem.getFoodId();
        this.foodPrice = cartItem.getFoodPrice();
        this.quantity = cartItem.getQuantity();
        this.orderEntity = order;
    }
}
