package com.swiggy.OrderMicroService.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.swiggy.OrderMicroService.utils.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"orderItems"})
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long restaurantId;
    @OneToMany(mappedBy = "orderEntity")
    private List<OrderCartItem> orderItems;
    private Status status;
    private LocalDateTime timeOfOrder;
    private LocalDateTime timeOfDelivery;
    private long deliveryAgentId;
    private long customerId;
    private String customerLocation;

}
