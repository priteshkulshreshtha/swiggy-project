package com.swiggy.UserMicroService.repository;

import com.swiggy.UserMicroService.entities.CustomizationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizationCategoryRepository extends JpaRepository<CustomizationCategory, Long> {

}
