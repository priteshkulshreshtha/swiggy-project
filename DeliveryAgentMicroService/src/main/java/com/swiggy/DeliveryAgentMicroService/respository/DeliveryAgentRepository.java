package com.swiggy.DeliveryAgentMicroService.respository;

import com.swiggy.DeliveryAgentMicroService.entity.DeliveryAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Long> {
    public Optional<DeliveryAgent> findFirstByContactNumber(String contactNumber);
    public Optional<DeliveryAgent> findFirstByBikeNumber(String bikeNumber);
}
