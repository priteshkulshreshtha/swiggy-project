package com.swiggy.FeignClientMicroService.beans.response.userClient;

public class NewUserProfileRequest {
    private String name;
    private String address;
    private String contactNumber;
    private String email;

    public NewUserProfileRequest(String name, String address, String contactNumber, String email) {
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public NewUserProfileRequest() {
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }


}
