package com.swiggy.FeignClientMicroService.beans.request.deliveryAgentClient;

public class DeliveryAgentRequest extends NewDeliveryAgentRequest {
    private long id;

    public DeliveryAgentRequest(String name, String address, String contactNumber, String bikeNumber, long id) {
        super(name, address, contactNumber, bikeNumber);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
