package com.swiggy.UserMicroService.repository;

import com.swiggy.UserMicroService.entities.CartItem;
import com.swiggy.UserMicroService.entities.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    public  Optional<CartItem> findFirstByFoodIdAndUserCart(long foodId, UserCart userCart);
    public void deleteAllByUserCart(UserCart cart);
}
