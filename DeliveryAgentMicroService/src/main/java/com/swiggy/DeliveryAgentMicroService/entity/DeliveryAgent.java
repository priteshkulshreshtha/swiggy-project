package com.swiggy.DeliveryAgentMicroService.entity;

import com.swiggy.DeliveryAgentMicroService.beans.request.NewDeliveryAgentRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAgent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PRIVATE)
    private long id;
    private String name;
    private String address;
    @Column(unique = true)
    private String contactNumber;
    @Column(unique = true)
    private String bikeNumber;
    private Long currentOrderId = null;

    public DeliveryAgent(NewDeliveryAgentRequest agentDetails) {
        this.name = agentDetails.getName();
        this.address = agentDetails.getAddress();
        this.bikeNumber = agentDetails.getBikeNumber();
        this.contactNumber = agentDetails.getContactNumber();
    }
}
