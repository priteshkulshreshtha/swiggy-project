package com.swiggy.UserMicroService.repository;

import com.swiggy.UserMicroService.entities.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCartRepository extends JpaRepository<UserCart, Long> {
}
