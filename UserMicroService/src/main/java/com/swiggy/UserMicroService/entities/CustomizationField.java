package com.swiggy.UserMicroService.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomizationField {


    @Id
    @GeneratedValue
    private long id;
    private String fieldName;
    private String description;
    private String price;
    private boolean isAddOn;

    private long fieldId;

    @ManyToOne()
    @JoinColumn(name="customization_category_id")
    private CustomizationCategory customizationCategory;

    @ManyToOne()
    @JoinColumn(name="user_cart_id")
    private UserCart userCart;

}
