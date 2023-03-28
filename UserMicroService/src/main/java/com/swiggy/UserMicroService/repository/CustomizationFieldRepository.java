package com.swiggy.UserMicroService.repository;

import com.swiggy.UserMicroService.entities.CustomizationField;
import com.swiggy.UserMicroService.entities.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomizationFieldRepository extends JpaRepository<CustomizationField, Long> {
    Optional<CustomizationField> findFirstByFieldIdAndUserCart(long fieldId, UserCart userCart);
}
