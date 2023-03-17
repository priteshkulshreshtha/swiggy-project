package com.swiggy.UserMicroService.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.swiggy.UserMicroService.beans.request.NewUserProfileRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"userCart"})
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    private String name;
    private String address;
    @Column(unique = true)
    private String contactNumber;
    @Column(unique = true)
    private String email;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userProfile")
    private UserCart userCart;

    public void copyUserData(NewUserProfileRequest userProfile) {
        this.setName(userProfile.getName());
        this.setAddress(userProfile.getAddress());
        this.setContactNumber(userProfile.getContactNumber());
        this.setEmail(userProfile.getEmail());
    }
}
