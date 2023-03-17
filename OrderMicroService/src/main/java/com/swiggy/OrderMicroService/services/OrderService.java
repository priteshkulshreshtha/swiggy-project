package com.swiggy.OrderMicroService.services;

import com.swiggy.OrderMicroService.beans.request.CartItemRequest;
import com.swiggy.OrderMicroService.beans.request.DeliveryAgentRequest;
import com.swiggy.OrderMicroService.beans.request.PlaceOrderRequest;
import com.swiggy.OrderMicroService.beans.response.OrderCartItemResponse;
import com.swiggy.OrderMicroService.beans.response.OrderResponse;
import com.swiggy.OrderMicroService.entity.OrderCartItem;
import com.swiggy.OrderMicroService.entity.OrderEntity;
import com.swiggy.OrderMicroService.exception.ResourceNotFoundException;
import com.swiggy.OrderMicroService.repository.CartItemRepository;
import com.swiggy.OrderMicroService.repository.OrderRepository;
import com.swiggy.OrderMicroService.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    OrderRepository orderRepository;

    public long createOrder(PlaceOrderRequest orderRequest) {

        OrderEntity newOrder = new OrderEntity();
        newOrder.setTimeOfOrder(orderRequest.getTimeOfOrder());
        newOrder.setStatus(Status.PLACED);
        newOrder.setCustomerId(orderRequest.getCustomerId());
        newOrder.setRestaurantId(orderRequest.getRestaurantId());
        newOrder.setCustomerLocation(orderRequest.getCustomerLocation());

        this.addCartItems(newOrder, orderRequest.getCartItems());

        return newOrder.getId();
    }


    public void addCartItems(OrderEntity order, List<CartItemRequest> cartItems) {
        List<OrderCartItem> orderCartItemList = new ArrayList<>();

        cartItems.stream().forEach(cartItem -> {
            orderCartItemList.add(new OrderCartItem(cartItem, order));
        });

        cartItemRepository.saveAll(orderCartItemList);
    }

    public OrderEntity getOrderById(long orderId) {
        Optional<OrderEntity> order =  orderRepository.findById(orderId);
        if (order.isEmpty()) throw new ResourceNotFoundException("No order found with this id.");
        return order.get();
    }

    public OrderResponse getOrderResponse(OrderEntity order) {
        OrderResponse orderResponse = new OrderResponse(order);
        List<OrderCartItem> cartItemList = cartItemRepository.findAllByOrderEntity(order);
        List<OrderCartItemResponse> cartItemResponseList = new ArrayList<>();


        cartItemList.stream().forEach(orderCartItem -> {
            cartItemResponseList.add(new OrderCartItemResponse(orderCartItem));
        });

        orderResponse.setOrderItems(cartItemResponseList);

        return orderResponse;
    }

    public OrderResponse getOrderResponse(long orderId) {
        OrderEntity order = getOrderById(orderId);
        return getOrderResponse(order);
    }


    public OrderEntity assignDelivery(DeliveryAgentRequest agent) {
        List<OrderEntity> orders = orderRepository.findAllByCustomerLocationAndStatus(agent.getLocation(), Status.PLACED);
        if (orders.isEmpty()) {
            return null;
        }
        else {
            int index = new Random().nextInt(orders.size());
            OrderEntity order = orders.get(index);
            order.setDeliveryAgentId(agent.getId());
            order.setStatus(Status.ASSIGNED);

            orderRepository.save(order);
            return order;
        }
    }

    public void orderDelivered(OrderEntity order) {
        order.setStatus(Status.DELIVERED);
        order.setTimeOfDelivery(LocalDateTime.now());
        orderRepository.save(order);
    }
}
