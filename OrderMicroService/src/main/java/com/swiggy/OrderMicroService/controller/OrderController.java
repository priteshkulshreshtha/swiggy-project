package com.swiggy.OrderMicroService.controller;

import com.swiggy.OrderMicroService.beans.request.DeliveryAgentRequest;
import com.swiggy.OrderMicroService.beans.request.PlaceOrderRequest;
import com.swiggy.OrderMicroService.beans.response.AgentOrderResponse;
import com.swiggy.OrderMicroService.beans.response.OrderResponse;
import com.swiggy.OrderMicroService.beans.response.ResponseWrapper;
import com.swiggy.OrderMicroService.entity.OrderEntity;
import com.swiggy.OrderMicroService.repository.CartItemRepository;
import com.swiggy.OrderMicroService.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    CartItemRepository cartItemRepository;

    @PostMapping("/create_order")
    public ResponseEntity<ResponseWrapper> createOrder(@RequestBody PlaceOrderRequest orderRequest) {
        long orderId = orderService.createOrder(orderRequest);
        Map<String, Long> response =new HashMap<>();
        response.put("orderId", orderId);

        return new ResponseWrapper().getResponse(
                response,
                "Order is placed",
                HttpStatus.CREATED
        );
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ResponseWrapper> getOrderById(@PathVariable long orderId) {
        OrderResponse order = orderService.getOrderResponse(orderId);
        return new ResponseWrapper().getResponse(order, "Fetched order with id: " + orderId, HttpStatus.OK);
    }

    @PostMapping("/assign_delivery")
    public ResponseEntity<ResponseWrapper> assignDelivery(@RequestBody DeliveryAgentRequest agent) {
        OrderEntity order = orderService.assignDelivery(agent);
        if (Objects.isNull(order)) return new ResponseWrapper().getResponse(null,"No orders available.", HttpStatus.NO_CONTENT);
        return new ResponseWrapper().getResponse(new AgentOrderResponse(order), "Order assigned. Order id: " + order.getId(), HttpStatus.OK);
    }

    @PostMapping("/order_delivered/{orderId}")
    public ResponseEntity<ResponseWrapper> orderDelivered(@PathVariable long orderId) {
        OrderEntity order = orderService.getOrderById(orderId);
        orderService.orderDelivered(order);
        return new ResponseWrapper().getResponse(null, "Order is marked delivered.", HttpStatus.OK);
    }

}
