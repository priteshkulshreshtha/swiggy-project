package com.swiggy.UserMicroService.repository;

import com.swiggy.UserMicroService.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    public Optional<UserProfile> findFirstByContactNumberOrEmail(String contactNumber, String email);
}
