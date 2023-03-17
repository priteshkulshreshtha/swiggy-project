package com.swiggy.OrderMicroService.repository;

import com.swiggy.OrderMicroService.entity.OrderEntity;
import com.swiggy.OrderMicroService.utils.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    public List<OrderEntity> findAllByCustomerLocationAndStatus(String customerLocation, Status status);
}
