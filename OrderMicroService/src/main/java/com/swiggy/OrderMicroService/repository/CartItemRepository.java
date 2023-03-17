package com.swiggy.OrderMicroService.repository;

import com.swiggy.OrderMicroService.entity.OrderCartItem;
import com.swiggy.OrderMicroService.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<OrderCartItem, Long> {

    public List<OrderCartItem> findAllByOrderEntity(OrderEntity order);

}
