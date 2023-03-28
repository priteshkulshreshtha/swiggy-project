package com.swiggy.UserMicroService.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomizationCategory {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @ManyToOne()
    @JoinColumn(name="user_cart_id")
    private UserCart userCart;

    @OneToMany(mappedBy = "customizationCategory")
    private List<CustomizationField> customizationFields;
}
