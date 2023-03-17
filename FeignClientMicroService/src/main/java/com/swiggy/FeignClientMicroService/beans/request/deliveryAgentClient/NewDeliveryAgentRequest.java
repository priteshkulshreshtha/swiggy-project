package com.swiggy.FeignClientMicroService.beans.request.deliveryAgentClient;

public class NewDeliveryAgentRequest {
    private String name;
    private String address;
    private String contactNumber;
    private String bikeNumber;


    public NewDeliveryAgentRequest(String name, String address, String contactNumber, String bikeNumber) {
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.bikeNumber = bikeNumber;
    }

    public NewDeliveryAgentRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getBikeNumber() {
        return bikeNumber;
    }

    public void setBikeNumber(String bikeNumber) {
        this.bikeNumber = bikeNumber;
    }
}
