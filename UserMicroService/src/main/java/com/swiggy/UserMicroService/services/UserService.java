package com.swiggy.UserMicroService.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swiggy.UserMicroService.beans.request.*;
import com.swiggy.UserMicroService.beans.response.ResponseWrapper;
import com.swiggy.UserMicroService.entities.UserCart;
import com.swiggy.UserMicroService.entities.UserProfile;
import com.swiggy.UserMicroService.exception.BadResponseDataException;
import com.swiggy.UserMicroService.exception.ResourceAlreadyExistException;
import com.swiggy.UserMicroService.exception.ResourceNotFoundException;
import com.swiggy.UserMicroService.repository.UserCartRepository;
import com.swiggy.UserMicroService.repository.UserProfileRepository;
import com.swiggy.UserMicroService.util.WebClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private UserCartRepository userCartRepository;
    @Autowired
    private WebClient.Builder webClient;

    @Autowired
    private WebClientUtils webClientUtils

    @Autowired
    private ObjectMapper objectMapper;

    public UserProfile getUserById(long userId) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(userId);
        if (userProfile.isEmpty()) throw new ResourceNotFoundException("User not found");
        return userProfile.get();
    }


    public void updateUserProfileService(UserProfileRequest newUserProfileDetails) {

        UserProfile oldUserProfile = getUserById(newUserProfileDetails.getId());

        oldUserProfile.copyUserData(newUserProfileDetails);
        userProfileRepository.save(oldUserProfile);
    }

    public void createUserProfileService(NewUserProfileRequest newUserProfileDetails) {

        Optional<UserProfile> checkUserExist  = userProfileRepository.findFirstByContactNumberOrEmail(
                newUserProfileDetails.getContactNumber(),
                newUserProfileDetails.getEmail()
        );

        if (checkUserExist.isEmpty()) {
            UserCart newUserCart = new UserCart();
            UserProfile newUserProfile = new UserProfile();
            newUserProfile.copyUserData(newUserProfileDetails);
            newUserProfile.setUserCart(newUserCart);
            newUserCart.setUserProfile(newUserProfile);
            userProfileRepository.save(newUserProfile);
            userCartRepository.save(newUserCart);
        } else {
            throw new ResourceAlreadyExistException("User already registered. Try Logging in.");
        }


    }


    public List<RestaurantRequest> getRestaurants(long userId) {
        UserProfile userProfile = getUserById(userId);
        try {
        RequestWrapper request = webClientUtils.getRequest(
                "http://localhost:8200/restaurant/city/" + userProfile.getAddress(),
                RequestWrapper.class
        );

            return objectMapper.convertValue(request.getData(), new TypeReference<List<RestaurantRequest>>() {});
        } catch (ClassCastException e) {
            throw new BadResponseDataException("Bad response from restaurant microservice");
        }
    }

    public List<FoodItemRequest> getFoodItems(long restaurantId) {
        RequestWrapper request = webClientUtils.getRequest(
                "http://localhost:8200/restaurant/" + restaurantId + "/food",
                ResponseWrapper.class
        );

        try {
            return objectMapper.convertValue(request.getData(), new TypeReference<List<FoodItemRequest>>() {});
        } catch (ClassCastException e) {
            throw new BadResponseDataException("Bad response from restaurant microservice");
        }

    }


}
