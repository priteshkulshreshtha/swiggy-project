package com.swiggy.FeignClientMicroService.beans.response.userClient;

public class UserProfileRequest extends NewUserProfileRequest {
    private long id;

    public UserProfileRequest(String name, String address, String contactNumber, String email, long id) {
        super(name, address, contactNumber, email);
        this.id = id;
    }

    public UserProfileRequest() {
        super();
    }

    public long getId() {
        return id;
    }

}
