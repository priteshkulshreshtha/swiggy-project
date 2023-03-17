package com.swiggy.DeliveryAgentMicroService.beans.response;

import com.swiggy.DeliveryAgentMicroService.entity.DeliveryAgent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryAgentResponse {
    private long id;
    private String name;
    private String address;
    private String contactNumber;
    private String bikeNumber;
    private Long currentOrderId = null;

    public DeliveryAgentResponse(DeliveryAgent agent) {
        this.id = agent.getId();
        this.name = agent.getName();
        this.address = agent.getAddress();
        this.contactNumber = agent.getContactNumber();
        this.bikeNumber = agent.getBikeNumber();
        this.currentOrderId = agent.getCurrentOrderId();
    }
}
