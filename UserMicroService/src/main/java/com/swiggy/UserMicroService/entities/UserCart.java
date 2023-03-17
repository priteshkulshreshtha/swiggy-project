package com.swiggy.UserMicroService.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.*;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class UserCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long restaurantId;
    @OneToMany(mappedBy = "userCart")
    private List<CartItem> orderItemList = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserProfile userProfile;
    private int billAmount = 0;

}
