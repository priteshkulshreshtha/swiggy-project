package com.swiggy.UserMicroService.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swiggy.UserMicroService.beans.request.FoodItemRequest;
import com.swiggy.UserMicroService.beans.response.PlaceOrderBody;
import com.swiggy.UserMicroService.beans.response.ResponseWrapper;
import com.swiggy.UserMicroService.entities.CartItem;
import com.swiggy.UserMicroService.entities.UserCart;
import com.swiggy.UserMicroService.entities.UserProfile;
import com.swiggy.UserMicroService.exception.ResourceNotFoundException;
import com.swiggy.UserMicroService.exception.RestaurantMismatchException;
import com.swiggy.UserMicroService.repository.CartItemRepository;
import com.swiggy.UserMicroService.repository.UserCartRepository;
import com.swiggy.UserMicroService.util.WebClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserCartService {

    @Autowired
    private UserCartRepository userCartRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private WebClient.Builder webClient;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    WebClientUtils webClientUtils;
    @Autowired
    private ObjectMapper objectMapper;

    public void clearCartService(long userId) {
        UserProfile userProfile = userService.getUserById(userId);
        UserCart userCart = userProfile.getUserCart();
        cartItemRepository.deleteAllByUserCart(userCart);
    }

    public void addFoodItemService(UserProfile userProfile, long foodId) {


        long userCartRestaurantId = userProfile.getUserCart().getRestaurantId();
        UserCart userCart = userProfile.getUserCart();

        FoodItemRequest foodItem = this.getFoodItemById(foodId);

        if (userCartRestaurantId == 0) {
            userCart.setRestaurantId(foodItem.getRestaurantId());
            this.addToOrderItems(foodItem, userCart);
            userCartRepository.save(userCart);
            return;
        }

        if(userCartRestaurantId != foodItem.getRestaurantId())
            throw new RestaurantMismatchException("Food items from multiple restaurants cannot be selected");

        this.addToOrderItems(foodItem, userCart);
        userCartRepository.save(userCart);

    }

    public List<CartItem> getCartOrderItems(long userId) {
        UserProfile userProfile = userService.getUserById(userId);
        return userProfile.getUserCart().getOrderItemList();
    }

    public void removeFoodItemService(UserProfile userProfile, long foodId) {
        UserCart userCart = userProfile.getUserCart();
        this.removeFoodItem(this.getFoodItemById(foodId), userCart);
        userCartRepository.save(userCart);
    }

    public FoodItemRequest getFoodItemById(long foodId) {

        ResponseWrapper response = webClientUtils.getRequest(
                "http://localhost:8200/food_item/" + foodId,
                ResponseWrapper.class
        );

        return objectMapper.convertValue(response.getData(), FoodItemRequest.class);

    }


    public void addToOrderItems(FoodItemRequest foodItem, UserCart userCart) {

        Optional<CartItem> cartItem = cartItemRepository.findFirstByFoodIdAndUserCart(foodItem.getId(), userCart);

        if (cartItem.isEmpty()) {
            CartItem newCartItem = new CartItem(foodItem);
            newCartItem.setUserCart(userCart);
            userCart.setBillAmount(userCart.getBillAmount()+ foodItem.getPrice());
            userCartRepository.save(userCart);
            cartItemRepository.save(newCartItem);
        }
        else {
            cartItem.get().setQuantity(cartItem.get().getQuantity() + 1);
            userCart.setBillAmount(userCart.getBillAmount()+ foodItem.getPrice());
            userCartRepository.save(userCart);
            cartItemRepository.save(cartItem.get());
        }
    }

    public void removeFoodItem(FoodItemRequest foodItem, UserCart userCart) {
        Optional<CartItem> cartItem = cartItemRepository.findFirstByFoodIdAndUserCart(foodItem.getId(), userCart);

        if (cartItem.isEmpty())
            throw new ResourceNotFoundException("Food item is not present in cart");

        if (cartItem.get().getQuantity() > 1) {
            cartItem.get().setQuantity(cartItem.get().getQuantity() - 1);
            cartItemRepository.save(cartItem.get());
            return;
        }
        else {
            cartItemRepository.deleteById(cartItem.get().getCartItemId());
        }
    }

    public Map<String, Long> placeOrder(UserProfile userProfile) {
        PlaceOrderBody order = new PlaceOrderBody();
        order.setCartItems(getCartOrderItems(userProfile.getId()));
        order.setCustomerId(userProfile.getId());
        order.setRestaurantId(userProfile.getUserCart().getRestaurantId());
        order.setTimeOfOrder(LocalDateTime.now());
        order.setCustomerLocation(userProfile.getAddress());
        order.setBillAmount(userProfile.getUserCart().getBillAmount());

        ResponseWrapper response = webClientUtils.postRequest(
                "http://localhost:8300/create_order",
                ResponseWrapper.class,
                order
        );

        return objectMapper.convertValue(response.getData(),  new TypeReference<Map<String, Long>>() {});

    }
}


